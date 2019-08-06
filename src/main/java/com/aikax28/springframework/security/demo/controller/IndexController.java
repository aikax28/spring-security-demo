/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aikax28.springframework.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
}
