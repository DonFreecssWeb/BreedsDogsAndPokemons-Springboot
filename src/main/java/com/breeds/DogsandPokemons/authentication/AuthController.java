package com.breeds.DogsandPokemons.authentication;

import com.breeds.DogsandPokemons.configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    JwtService jwtService;
    @GetMapping
    public ResponseEntity<String> generateToken(){
        return  ResponseEntity.ok().body(jwtService.generateToken());
    }
}
