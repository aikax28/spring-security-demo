package com.aikax28.springframework.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author aikax28
 */
@RestController
public class IndexController {

    @GetMapping
    public Mono<String> ok() {
        return Mono.just("ok");
    }

    @PostMapping
    public Mono<String> okForPost() {
        return Mono.just("ok");
    }
}
