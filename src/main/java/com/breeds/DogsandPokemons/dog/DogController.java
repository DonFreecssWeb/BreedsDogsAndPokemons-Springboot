package com.breeds.DogsandPokemons.dog;

import com.breeds.DogsandPokemons.configuration.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/dogs")
public class DogController {

    @Autowired
    DogService dogService;

    @Autowired
    JwtService jwtService;
    @GetMapping("/breeds")
    public ResponseEntity<Object> getAllBreeds(
            @RequestHeader("Authorization") String authorization
    ){
        try {
            if(authorization.isBlank()){
                return ResponseEntity.status(401).body("Requiere una autorización");
            }
            if(jwtService.validateToken(authorization)){
                return ResponseEntity.ok().body(dogService.getAllBreeds());
            }else {
                return ResponseEntity.status(401).body("Token no válido");
            }
        }catch (ExpiredJwtException e){
            return ResponseEntity.status(401).body("Token expiró");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }


    }
}
