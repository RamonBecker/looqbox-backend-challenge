package com.looqbox.pokemon.utils;

import com.looqbox.pokemon.domain.entities.Letter;
import com.looqbox.pokemon.domain.entities.Pokemon;

import java.util.List;

public class UtilList {
    public static Letter searchBinary(Character x, List<Letter> letters) {
        int start = 0;
        int middle = 0;
        int end = letters.size() - 1;

        while (start <= end) {
            middle = (end + start) / 2;
            if (letters.get(middle).getLetter() == x) {
                return letters.get(middle);
            }
            if (letters.get(middle).getLetter() < x) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        if (start > end) {
            return null;
        }

        return null;
    }

    public static boolean searchPokemon(Pokemon pokemonSearch, List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().equals(pokemonSearch.getName())) {
                return true;
            }
        }
        return false;
    }

}
