package com.xyz.retail.product.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PingController {
    @GetMapping("/ping")
    public String ping() { return "pong"; }
}
