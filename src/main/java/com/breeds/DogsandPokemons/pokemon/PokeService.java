package com.breeds.DogsandPokemons.pokemon;

import com.breeds.DogsandPokemons.dog.Dog;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class PokeService {
    public Pokemon getAllPokemon() {
        Random random = new Random();
         int id = random.nextInt(1,1282);
            String url = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
            RestTemplate restTemplate = new RestTemplate();
        Pokemon response = restTemplate.getForObject(url, Pokemon.class);
            return response;
    }
}
