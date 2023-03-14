package com.breeds.DogsandPokemons.pokemon;

import com.breeds.DogsandPokemons.configuration.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokeController {
@Autowired
    PokeService pokeService;
    @Autowired
    JwtService jwtService;
    @GetMapping({"/get-all"})
    public ResponseEntity<Object> getPokemonData(
            @RequestHeader("Authorization") String authorization
    ){
        if(authorization.isBlank()){
            return ResponseEntity.status(401).body("Requiere una autorización");
        }
        try {
            if(jwtService.validateToken(authorization)){
                return ResponseEntity.ok().body(pokeService.getAllPokemon());
            }else{
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
