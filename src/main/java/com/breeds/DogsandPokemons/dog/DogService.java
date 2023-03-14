package com.breeds.DogsandPokemons.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogService {

    public Dog getAllBreeds() {
        String url = "https://dog.ceo/api/breeds/list/all";
        RestTemplate restTemplate = new RestTemplate();
        Dog response = restTemplate.getForObject(url, Dog.class);
        return response;
    }
}
