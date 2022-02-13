package com.looqbox.pokemon.domain.impl;

import com.looqbox.pokemon.domain.IGroupBy;
import com.looqbox.pokemon.domain.IOrderBy;
import com.looqbox.pokemon.domain.entities.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.looqbox.pokemon.utils.UtilString.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderByImplTest {

    private static final int INDEX = 0;
    private IGroupBy IGroupBy;
    private IOrderBy IOrderBy;
    private List<Pokemon> pokemons_disorderly;
    private List<Pokemon> pokemons_orderly;
    private List<GroupByImpl> result_groupBy_pokemons;
    private List<GroupByImpl> result_groupByNameLength_pokemons;
    private List<GroupByImpl> result_orderByNameLength_pokemons;
    private List<GroupByImpl> result_orderByName_pokemons;

    @BeforeEach
    void setUp() {
        startOrderBy();
    }

    @Test
    void orderByName() {
        result_groupBy_pokemons = IGroupBy.groupByPokemons(pokemons_disorderly);
        result_orderByNameLength_pokemons = IOrderBy.orderByNameLength(result_groupBy_pokemons);
        result_groupByNameLength_pokemons = IGroupBy.groupByNameLength(result_orderByNameLength_pokemons);
        result_orderByName_pokemons = IOrderBy.orderByName(result_groupBy_pokemons, result_groupByNameLength_pokemons);

        assertNotNull(result_groupBy_pokemons);
        assertNotNull(result_orderByNameLength_pokemons);
        assertNotNull(result_groupByNameLength_pokemons);
        assertNotNull(result_orderByName_pokemons);

        assertNotEquals(0, result_groupBy_pokemons.size());
        assertNotEquals(0, result_orderByNameLength_pokemons.size());
        assertNotEquals(0, result_groupByNameLength_pokemons.size());
        assertNotEquals(0, result_orderByName_pokemons.size());

        assertNotNull(result_groupBy_pokemons.get(INDEX).getPokemons());
        assertNotNull(result_orderByNameLength_pokemons.get(INDEX).getPokemons());
        assertNotNull(result_groupByNameLength_pokemons.get(INDEX).getPokemons());
        assertNotNull(result_orderByName_pokemons.get(INDEX).getPokemons());

        assertEquals(GroupByImpl.class, result_groupBy_pokemons.get(INDEX).getClass());
        assertEquals(GroupByImpl.class, result_orderByNameLength_pokemons.get(INDEX).getClass());
        assertEquals(GroupByImpl.class, result_groupByNameLength_pokemons.get(INDEX).getClass());
        assertEquals(GroupByImpl.class, result_orderByName_pokemons.get(INDEX).getClass());


        assertEquals(Pokemon.class, result_orderByName_pokemons.get(INDEX).getPokemons().get(INDEX).getClass());
        assertEquals(pokemons_orderly.size(), result_orderByName_pokemons.get(INDEX).getPokemons().size());
        assertEquals(pokemons_orderly.get(0).getName(), result_orderByName_pokemons.get(INDEX).getPokemons().get(0).getName());
        assertEquals(pokemons_orderly.get(1).getName(), result_orderByName_pokemons.get(INDEX).getPokemons().get(1).getName());
    }

    @Test
    void orderByNameLength() {
        result_groupBy_pokemons = IGroupBy.groupByPokemons(pokemons_disorderly);
        result_orderByNameLength_pokemons = IOrderBy.orderByNameLength(result_groupBy_pokemons);

        assertNotNull(result_groupBy_pokemons);
        assertNotNull(result_orderByNameLength_pokemons);

        assertNotEquals(0, result_groupBy_pokemons.size());
        assertNotEquals(0, result_orderByNameLength_pokemons.size());

        assertNotNull(result_orderByNameLength_pokemons.get(INDEX).getPokemons());
        assertNotEquals(0, result_orderByNameLength_pokemons.get(INDEX).getPokemons().size());

        assertEquals(pokemons_disorderly.size(), result_orderByNameLength_pokemons.get(INDEX).getPokemons().size());
        assertEquals(Pokemon.class, result_orderByNameLength_pokemons.get(INDEX).getPokemons().get(0).getClass());

        assertEquals(NAME_POKEMON_ELEMENT_0, result_orderByNameLength_pokemons.get(INDEX).getPokemons().get(0).getName());
        assertEquals(NAME_POKEMON_ELEMENT_2, result_orderByNameLength_pokemons.get(INDEX).getPokemons().get(1).getName());

    }

    private void startOrderBy() {
        IGroupBy = new GroupByImpl();
        IOrderBy = new OrderByImpl();
        pokemons_disorderly = new ArrayList<>();
        pokemons_orderly = new ArrayList<>();

        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_2));

        pokemons_orderly.add(new Pokemon(NAME_POKEMON_ELEMENT_2));
        pokemons_orderly.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
    }
}