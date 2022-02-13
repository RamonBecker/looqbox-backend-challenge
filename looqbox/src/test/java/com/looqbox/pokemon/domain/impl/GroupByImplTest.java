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

class GroupByImplTest {

    private static final int INDEX = 0;
    private IGroupBy groupBy;
    private IOrderBy IOrderBy;
    private List<Pokemon> pokemons_disorderly;
    private List<Pokemon> pokemons_groupByNameLength;
    private List<GroupByImpl> result_groupBy_pokemons;
    private List<GroupByImpl> result_orderByNameLength_pokemons;
    private List<GroupByImpl> result_groupByNameLength_pokemons;

    @BeforeEach
    void setUp() {
        startGroupBy();
    }

    @Test
    void groupByPokemons() {
        result_groupBy_pokemons = groupBy.groupByPokemons(pokemons_disorderly);
        assertNotNull(result_groupBy_pokemons);
        assertNotEquals(0, result_groupBy_pokemons.size());

        assertEquals(GroupByImpl.class, result_groupBy_pokemons.get(INDEX).getClass());
        assertEquals(POKEMON_LETTER, result_groupBy_pokemons.get(INDEX).getLetter().getLetter());
        assertNotNull(result_groupBy_pokemons.get(INDEX).getPokemons());

        assertNotEquals(0, result_groupBy_pokemons.get(INDEX).getPokemons().size());
        assertEquals(Pokemon.class, result_groupBy_pokemons.get(INDEX).getPokemons().get(INDEX).getClass());
        assertEquals(NAME_POKEMON_ELEMENT_0, result_groupBy_pokemons.get(INDEX).getPokemons().get(INDEX).getName());
    }

    @Test
    void groupByNameLength() {
        result_groupBy_pokemons = groupBy.groupByPokemons(pokemons_disorderly);
        result_orderByNameLength_pokemons = IOrderBy.orderByNameLength(result_groupBy_pokemons);
        result_groupByNameLength_pokemons = groupBy.groupByNameLength(result_orderByNameLength_pokemons);

        assertNotNull(result_groupBy_pokemons);
        assertNotNull(result_orderByNameLength_pokemons);
        assertNotNull(result_groupByNameLength_pokemons);

        assertNotEquals(0, result_groupBy_pokemons.size());
        assertNotEquals(0, result_orderByNameLength_pokemons.size());
        assertNotEquals(0, result_groupByNameLength_pokemons.size());

        assertEquals(GroupByImpl.class, result_groupBy_pokemons.get(INDEX).getClass());
        assertEquals(GroupByImpl.class, result_orderByNameLength_pokemons.get(INDEX).getClass());
        assertEquals(GroupByImpl.class, result_groupByNameLength_pokemons.get(INDEX).getClass());

        assertNotNull(result_groupByNameLength_pokemons.get(INDEX).getPokemons());
        assertNotEquals(0, result_groupByNameLength_pokemons.get(INDEX).getPokemons().size());
        assertEquals(pokemons_groupByNameLength.size(),result_groupByNameLength_pokemons.get(INDEX).getPokemons().size());
        assertNotNull(result_groupByNameLength_pokemons.get(INDEX).getPokemons().get(INDEX));
        assertEquals(Pokemon.class, result_groupByNameLength_pokemons.get(INDEX).getPokemons().get(INDEX).getClass());
        assertEquals(pokemons_groupByNameLength.get(INDEX).getName(), result_groupByNameLength_pokemons.get(INDEX).getPokemons().get(INDEX).getName());
    }

    private void startGroupBy() {
        groupBy = new GroupByImpl();
        IOrderBy = new OrderByImpl();
        pokemons_disorderly = new ArrayList<>();
        pokemons_groupByNameLength = new ArrayList<>();

        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_1));
        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_2));

        pokemons_groupByNameLength.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons_groupByNameLength.add(new Pokemon(NAME_POKEMON_ELEMENT_2));
    }
}