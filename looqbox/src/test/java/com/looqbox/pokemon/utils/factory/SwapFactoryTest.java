package com.looqbox.pokemon.utils.factory;

import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.domain.impl.SwapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.looqbox.pokemon.utils.UtilString.*;
import static org.junit.jupiter.api.Assertions.*;

class SwapFactoryTest {

    private int i;
    private List<Pokemon> pokemons;
    private List<Pokemon> pokemons_order;


    @BeforeEach
    void setUp() {
        startSwapFactory();
    }

    @Test
    void createSwap() {
        SwapImpl result =  SwapFactory.createSwap(i, pokemons, pokemons_order);
       assertNotNull(result);
       assertEquals(SwapImpl.class, result.getClass());
       assertInstanceOf(SwapImpl.class, result);
    }

    private void startSwapFactory() {
        i = 0;
        pokemons = new ArrayList<>();
        pokemons_order = new ArrayList<>();

        pokemons.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons.add(new Pokemon(NAME_POKEMON_ELEMENT_1));
        pokemons.add(new Pokemon(NAME_POKEMON_ELEMENT_2));

        pokemons_order.add(new Pokemon(NAME_POKEMON_ELEMENT_2));
        pokemons_order.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons_order.add(new Pokemon(NAME_POKEMON_ELEMENT_1));
    }
}