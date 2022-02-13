package com.looqbox.pokemon.services;

import com.looqbox.pokemon.domain.entities.Pokemon;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IPokemonService {

    List<Pokemon> getPokemon(@PathVariable String name);
    List<Pokemon> getAllPokemons();
}
