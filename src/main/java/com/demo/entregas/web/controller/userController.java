package com.demo.entregas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/users")
@RestController
public class userController {

    @GetMapping
    public ResponseEntity teste(){
        return ResponseEntity.ok("teste");
    }
}
