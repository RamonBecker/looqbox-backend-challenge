package com.looqbox.pokemon.utils.factory;

import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.domain.impl.SwapImpl;

import java.util.List;

public class SwapFactory {

    public static SwapImpl createSwap(int i, List<Pokemon> pokemons, List<Pokemon> pokemons_order) {
        return new SwapImpl(i, pokemons, pokemons_order);
    }
}
