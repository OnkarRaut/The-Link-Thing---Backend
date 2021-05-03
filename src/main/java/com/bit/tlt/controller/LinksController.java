package com.bit.tlt.controller;

import com.bit.tlt.model.Link;
import com.bit.tlt.model.TltResponse;
import com.bit.tlt.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinksController {

    private LinkService linkService;

    @Value("${security.auth.key}")
    private String key;

    @Autowired
    public LinksController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public ResponseEntity<TltResponse<List<Link>>> getLinks() {
        return ResponseEntity.ok(
                TltResponse.<List<Link>>builder()
                    .body(this.linkService.getLinks())
                    .httpStatus(HttpStatus.OK)
                    .httpStatusCode(HttpStatus.OK.value())
                    .build()
        );
    }

    @PostMapping
    public ResponseEntity<TltResponse<Link>> saveLink(@RequestHeader("user-ref") String userRef, @RequestBody Link link) {
        return ResponseEntity.ok(
                TltResponse.<Link>builder()
                        .body(this.linkService.saveLink(link, userRef))
                        .httpStatus(HttpStatus.OK)
                        .httpStatusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/me")
    public ResponseEntity<TltResponse<List<Link>>> getMyLinks(@RequestHeader("user-ref") String userRef) {
        return ResponseEntity.ok(
                TltResponse.<List<Link>>builder()
                    .body(this.linkService.getLinksByUsername(userRef))
                    .httpStatus(HttpStatus.OK)
                    .httpStatusCode(HttpStatus.OK.value())
                    .build()
        );
    }

}
