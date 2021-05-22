package com.bit.tlt.service;

import com.bit.tlt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getUser(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Username not found");
        }

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .credentialsExpired(false)
            .accountExpired(false)
            .accountLocked(false)
            .authorities(Collections.emptyList())
            .build();
    }

    public Long findUserIdByUsername(String username) {
        return this.userRepository.findIdByUsername(username);
    }

}
