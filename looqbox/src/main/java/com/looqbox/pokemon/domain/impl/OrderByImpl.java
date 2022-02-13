package com.looqbox.pokemon.domain.impl;

import com.looqbox.pokemon.domain.IOrderBy;
import com.looqbox.pokemon.domain.entities.Letter;
import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.utils.factory.SwapFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.looqbox.pokemon.utils.UtilList.searchPokemon;

@NoArgsConstructor
@Getter
public class OrderByImpl implements IOrderBy {
    private SwapImpl swap;
    private List<Pokemon> pokemons;
    private List<Pokemon> pokemons_order;

    @Override
    public List<GroupByImpl> orderByName(List<GroupByImpl> letters, List<GroupByImpl> groupByPokemonsNameLength) {
        List<GroupByImpl> filter = new ArrayList<GroupByImpl>();
        Pokemon pokemon_swap = null;
        Letter letter_swap = null;

        for (GroupByImpl letter : letters) {
            for (GroupByImpl groupByPokemonNameLength : groupByPokemonsNameLength) {
                pokemons_order = new ArrayList<Pokemon>();
                if (groupByPokemonNameLength.getLetter().getLetter().equals(letter.getLetter().getLetter())) {
                    pokemons = groupByPokemonNameLength.getPokemons();
                    int i = 0;
                    while (true) {
                        Pokemon pokemon_current = pokemons.get(i);
                        boolean found_pokemon_current = searchPokemon(pokemon_current, pokemons_order);
                        if (!found_pokemon_current) {
                            swap = SwapFactory.createSwap(i ,pokemons, pokemons_order);
                            if(swap.swap(pokemon_current, pokemon_swap, letter_swap)){
                                i = swap.getI();
                                pokemons_order = swap.getPokemons_order();
                                break;
                            }
                        } else {
                            i++;
                        }
                    }
                    filter.add(new GroupByImpl(groupByPokemonNameLength.getLetter(), groupByPokemonNameLength.getStart(),
                            groupByPokemonNameLength.getEnd(), pokemons_order));
                }
            }
        }
        return filter;

    }
    @Override
    public List<GroupByImpl> orderByNameLength(List<GroupByImpl> groupByPokemons) {
        Pokemon pokemon_swap = null;
        List<GroupByImpl> filter = new ArrayList<GroupByImpl>();
        for (GroupByImpl groupByPokemon : groupByPokemons) {
            pokemons = groupByPokemon.getPokemons();
            pokemons_order = new ArrayList<Pokemon>();
            int i = 0;
            while (true) {
                Pokemon pokemon_current = pokemons.get(i);
                boolean found_pokemon_current = searchPokemon(pokemon_current, pokemons_order);
                if (!found_pokemon_current) {
                    swap = SwapFactory.createSwap(i ,pokemons, pokemons_order);
                    if(swap.swap(pokemon_current, pokemon_swap)){
                         i = swap.getI();
                         pokemons_order = swap.getPokemons_order();
                         break;
                    }

                } else {
                    i++;
                }

                if (i == pokemons.size()) {
                    break;
                }
            }
            filter.add(new GroupByImpl(groupByPokemon.getLetter(), pokemons_order));
        }
        return filter;
    }
}
