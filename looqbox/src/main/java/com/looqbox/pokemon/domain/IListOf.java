package com.looqbox.pokemon.domain;

import com.looqbox.pokemon.domain.entities.Pokemon;

import java.util.List;

public interface IListOf {

    List<Pokemon> contains(String pokemonSearch, List<Pokemon> pokemons);
    List<Pokemon> orderByPokemon(List<Pokemon> pokemons);
}
