package com.looqbox.pokemon.resources;

import com.looqbox.pokemon.dto.ListOfPokemonDTO;
import com.looqbox.pokemon.dto.response.PokemonDTOResponse;
import com.looqbox.pokemon.services.IPokemonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.looqbox.pokemon.utils.UtilString.URL_POKEMON;

@AllArgsConstructor
@RestController
@RequestMapping(URL_POKEMON)
public class PokemonResource {

    @Autowired
    private IPokemonService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<ListOfPokemonDTO> getPokemon(@RequestParam(value = "q", required = true) String name){
        ListOfPokemonDTO listOfPokemonDTO = new ListOfPokemonDTO();
        listOfPokemonDTO.setResults(service.getPokemon(name).stream().map(x -> mapper.map(x, PokemonDTOResponse.class)).collect(Collectors.toList()));
        return ResponseEntity.ok(listOfPokemonDTO);
    }
}
