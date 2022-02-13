package com.looqbox.pokemon.domain;

import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.domain.impl.GroupByImpl;

import java.util.List;

public interface IGroupBy {
    List<GroupByImpl> groupByPokemons(List<Pokemon> pokemons);
    List<GroupByImpl> groupByNameLength(List<GroupByImpl> groupByPokemons);
}
