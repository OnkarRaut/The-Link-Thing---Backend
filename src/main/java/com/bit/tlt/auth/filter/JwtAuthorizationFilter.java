package com.bit.tlt.auth.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.bit.tlt.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Log4j2
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String[] UNSECURED_APIS = new String[] {"/auth", "/swagger-ui.html", "/webjars", "/v2", "/swagger-resources"};

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${security.auth.key}")
    private String key;

    private UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthorizationFilter(@Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.startsWithAny(httpServletRequest.getRequestURI().replaceAll(contextPath, ""), UNSECURED_APIS)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        try {
            String token = this.getAuthToken(httpServletRequest);
            if (StringUtils.isAllBlank(token)) {
                response(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, "Required Authorization token not found");
                return;
            }

            Claims claims = JWTUtil.extractClaims(token, key);
            if (Objects.nonNull(claims.getSubject()) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(claims.getSubject());
                if (claims.getSubject().equals(userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } catch (JWTDecodeException | IllegalArgumentException | MalformedJwtException e) {
            response(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, "Invalid access token");
        } catch (ExpiredJwtException e) {
            response(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, "Access token expired");
        }
    }

    private String getAuthToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.startsWith(token, "Bearer")) {
            return token.substring("Bearer".length());
        }
        return null;
    }

    private void response(HttpServletResponse response, int status, String content) throws IOException {
        response.resetBuffer();
        response.getWriter().write(content);
        response.setStatus(status);
        response.flushBuffer();
    }

}
