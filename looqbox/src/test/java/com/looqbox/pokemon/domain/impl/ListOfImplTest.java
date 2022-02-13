package com.looqbox.pokemon.domain.impl;

import com.looqbox.pokemon.domain.IListOf;
import com.looqbox.pokemon.domain.entities.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.looqbox.pokemon.utils.UtilString.*;
import static org.junit.jupiter.api.Assertions.*;

class ListOfImplTest {

    private static final int INDEX = 0;
    private IListOf IListOf;
    private List<Pokemon> pokemons_disorderly;
    private List<Pokemon> pokemons_orderly;

    @BeforeEach
    void setUp() {
        startListOf();
    }

    @Test
    void contains() {
       List<Pokemon> result =  IListOf.contains(NAME_POKEMON_SEARCH, pokemons_disorderly);
       assertNotNull(result);
       assertNotEquals(0, result.size());
       assertEquals(pokemons_disorderly.size(), result.size());
       assertEquals(Pokemon.class, result.get(INDEX).getClass());
       assertEquals(NAME_POKEMON_ELEMENT_0, result.get(INDEX).getName());
    }

    @Test
    void orderByPokemon() {
        List<Pokemon> result_pokemons_disorderly =  IListOf.contains(NAME_POKEMON_SEARCH, pokemons_disorderly);
        List<Pokemon> result_pokemons_orderly = IListOf.orderByPokemon(result_pokemons_disorderly);

        assertNotNull(result_pokemons_disorderly);
        assertNotEquals(0, result_pokemons_disorderly.size());
        assertEquals(Pokemon.class, result_pokemons_disorderly.get(INDEX).getClass());

        assertNotNull(result_pokemons_orderly);
        assertNotEquals(0, result_pokemons_orderly.size());
        assertEquals(Pokemon.class, result_pokemons_disorderly.get(INDEX).getClass());

        assertEquals(pokemons_orderly.size(), result_pokemons_orderly.size());
        assertEquals(pokemons_orderly.get(INDEX).getName(), result_pokemons_orderly.get(INDEX).getName());
    }

    private void startListOf(){
        IListOf = new ListOfImpl();
        pokemons_disorderly = new ArrayList<>();
        pokemons_orderly = new ArrayList<>();

        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_1));
        pokemons_disorderly.add(new Pokemon(NAME_POKEMON_ELEMENT_2));

        pokemons_orderly.add(new Pokemon(NAME_POKEMON_ELEMENT_1));
        pokemons_orderly.add(new Pokemon(NAME_POKEMON_ELEMENT_2));
        pokemons_orderly.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
    }
}