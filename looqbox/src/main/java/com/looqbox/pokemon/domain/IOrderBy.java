package com.looqbox.pokemon.domain;

import com.looqbox.pokemon.domain.impl.GroupByImpl;

import java.util.List;

public interface IOrderBy {

    List<GroupByImpl> orderByName(List<GroupByImpl> letters, List<GroupByImpl> groupByPokemonsNameLength);
    List<GroupByImpl> orderByNameLength(List<GroupByImpl> groupByPokemons);
}
