package com.looqbox.pokemon.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    private String name;
    private int start;
    private int end;

    public Pokemon(String name){
        this.name = name;
    }
}
