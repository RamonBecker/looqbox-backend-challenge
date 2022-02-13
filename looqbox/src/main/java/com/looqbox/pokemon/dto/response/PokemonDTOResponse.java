package com.looqbox.pokemon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTOResponse {

    private String name;
    private int start;
    private int end;

    public PokemonDTOResponse(String name){
        this.name = name;
    }

}
