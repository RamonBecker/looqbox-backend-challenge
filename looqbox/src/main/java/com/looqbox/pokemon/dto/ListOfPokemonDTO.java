package com.looqbox.pokemon.dto;

import com.looqbox.pokemon.dto.response.PokemonDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfPokemonDTO {
    private List<PokemonDTOResponse> results;
}
