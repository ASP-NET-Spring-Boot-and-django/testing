package com.example.demo5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class recipeController {



    @GetMapping("/recipe")
    public ResponseEntity<String> getRecipes(){
    return ResponseEntity.ok("hello world");
    }

    @PostMapping("/recipe")
    public ResponseEntity<String> createRecipe(){
        return ResponseEntity.ok("hello world");
    }





}
