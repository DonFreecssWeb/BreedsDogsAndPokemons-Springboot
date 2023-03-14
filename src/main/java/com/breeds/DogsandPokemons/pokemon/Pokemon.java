package com.breeds.DogsandPokemons.pokemon;

import lombok.Data;

import java.util.List;

@Data
public class Pokemon {
    private String count;
    private String next;
    private String previous;
    private List<Object> results;
}
