package com.demo.entregas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/produtos")
@RestController
public class produtoController {
    @GetMapping
    public ResponseEntity chamada() {
        return ResponseEntity.ok("teste produto");
    }
}
