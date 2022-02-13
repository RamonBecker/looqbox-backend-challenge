package com.looqbox.pokemon.utils;

import com.looqbox.pokemon.domain.entities.Letter;
import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.utils.factory.AlphabetFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.looqbox.pokemon.utils.UtilString.*;
import static org.junit.jupiter.api.Assertions.*;

class UtilListTest {

    private static final int INDEX = 0;

    private List<Pokemon> pokemons;
    private Pokemon pokemonSearch;
    private List<Letter> alphabet;

    @BeforeEach
    void setUp() {
        startAlphabet();
        startPokemons();
    }

    @Test
    void searchBinary() {
        assertNotNull(alphabet);
        assertEquals(Letter.class, alphabet.get(INDEX).getClass());
        Letter letter = UtilList.searchBinary(POKEMON_LETTER, alphabet);
        assertNotNull(letter);
        assertEquals(POKEMON_LETTER, letter.getLetter());
    }

    @Test
    void searchPokemon() {
        assertNotNull(pokemonSearch);
        assertNotNull(pokemons);
        assertNotEquals(0, pokemons.size());
        assertTrue(UtilList.searchPokemon(pokemonSearch, pokemons));
    }

    private void startPokemons() {
        pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(NAME_POKEMON_ELEMENT_0));
        pokemons.add(new Pokemon(NAME_POKEMON_ELEMENT_1));
        pokemons.add(new Pokemon(NAME_POKEMON_ELEMENT_2));

        pokemonSearch = new Pokemon(NAME_POKEMON_ELEMENT_2);
    }

    private void startAlphabet() {
        alphabet = AlphabetFactory.createAlphabet();
    }

}