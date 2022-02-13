package com.looqbox.pokemon.domain.impl;


import com.looqbox.pokemon.domain.IGroupBy;
import com.looqbox.pokemon.domain.IListOf;
import com.looqbox.pokemon.domain.IOrderBy;
import com.looqbox.pokemon.domain.entities.Pokemon;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListOfImpl implements IListOf {

    private IOrderBy orderBy;
    private IGroupBy groupBy;
    private List<GroupByImpl> groupByPokemonsOrderName;
    private List<GroupByImpl> groupByPokemonsOrderNameLength;
    private List<GroupByImpl> groupByPokemons;
    private List<GroupByImpl> groupByPokemonsLength;

    public ListOfImpl() {
        this.orderBy = new OrderByImpl();
        this.groupBy = new GroupByImpl();
    }

    @Override
    public List<Pokemon> contains(String pokemonSearch, List<Pokemon> pokemons) {
        int i = 0;
        int k = 0;
        int l = 0;
        int m = 0;
        int index_letter_next_not_found = -1;
        boolean last_element = false;

        List<Pokemon> filter = new ArrayList<Pokemon>();

        for (Pokemon pokemon : pokemons) {
            String name = pokemon.getName().toLowerCase();
            while (true) {
                if (pokemonSearch.length() > name.length()) {
                    break;
                }
                Character c = pokemonSearch.charAt(i);
                boolean letter_current_found = false;
                boolean letter_next_not_found = false;
                for (int j = k; j < name.length(); j++) {
                    if (c == name.charAt(j)) {
                        if (i + 1 < pokemonSearch.length()) {
                            if (j + 1 < name.length()) {
                                if (pokemonSearch.charAt(i + 1) != name.charAt(j + 1)) {
                                    letter_next_not_found = true;
                                    index_letter_next_not_found = j;
                                    break;
                                }
                            }
                        }
                        letter_current_found = true;
                        l = j;
                        break;
                    }
                    m = j;
                }

                if (letter_next_not_found) {
                    int index = -1;
                    if (index_letter_next_not_found > 0) {
                        index_letter_next_not_found++;
                        if (index_letter_next_not_found < name.length()) {
                            for (int j = index_letter_next_not_found; j < name.length(); j++) {
                                if (c == name.charAt(j)) {
                                    index = j;
                                    break;
                                }
                            }
                            index_letter_next_not_found = -1;
                        }
                    }
                    if (index > 0) {
                        if (index + 1 < name.length()) {
                            if (i + 1 < pokemonSearch.length()) {
                                if (name.charAt(index + 1) != pokemonSearch.charAt(i + 1)) {
                                    break;
                                }
                            }
                        }
                        k = index;
                    } else {
                        break;
                    }
                }

                if (letter_current_found) {
                    boolean letter_next_found = false;
                    boolean letter_next_not_found_seq = false;
                    if (i == 0) {
                        if (pokemonSearch.length() == 1) {
                            pokemon.setStart(l);
                            i++;
                        } else {
                            if (i + 1 < pokemonSearch.length()) {
                                if (l + 1 < name.length()) {
                                    int index = i + 1;
                                    int count = 0;
                                    int index_next = -1;
                                    for (int j = l + 1; j < name.length(); j++) {
                                        if (pokemonSearch.charAt(index) != name.charAt(j)) {
                                            index_next = j;
                                            break;
                                        } else {
                                            count++;
                                        }
                                        if (count == pokemonSearch.length() - 1) {
                                            break;
                                        }
                                        index++;
                                    }
                                    if (count == pokemonSearch.length() - 1) {
                                        pokemon.setStart(l);
                                        i++;
                                        letter_next_found = true;
                                    } else {
                                        if (index_next < 0) {
                                            last_element = true;
                                        } else {
                                            k = index_next;
                                        }
                                        letter_next_not_found_seq = true;
                                    }
                                }
                            }
                        }
                    }
                    if (pokemonSearch.length() > 1) {
                        if (l > 0) {
                            if (!letter_next_not_found_seq) {
                                k = l;
                            }
                            if (k + 1 == name.length()) {
                                if (name.charAt(k - 1) == pokemonSearch.charAt(i)) {
                                    i++;
                                } else if (name.charAt(k) == pokemonSearch.charAt(i)) {
                                    i++;
                                    last_element = true;
                                } else {
                                    last_element = true;
                                }
                            } else {
                                if (!letter_next_not_found_seq) {
                                    if (i + 1 < pokemonSearch.length()) {
                                        if (k + 1 < name.length()) {
                                            if (pokemonSearch.charAt(i + 1) == name.charAt(k + 1)) {
                                                k++;
                                            }
                                        }
                                    }
                                    if (!letter_next_found) {
                                        i++;
                                    } else {
                                        k++;
                                    }
                                }
                            }
                        } else {
                            k++;
                        }
                    }
                }

                if (i == pokemonSearch.length()) {
                    pokemon.setEnd(l + 1);
                    break;
                } else if (m + 1 == name.length()) {
                    break;
                } else if (last_element) {
                    break;
                } else if (k >= name.length()) {
                    break;
                }
            }

            if (i == pokemonSearch.length()) {
                filter.add(pokemon);
            }

            k = 0;
            i = 0;
            m = 0;
            last_element = false;

        }
        return filter;
    }

    @Override
    public List<Pokemon> orderByPokemon(List<Pokemon> pokemons) {
        groupByPokemons = groupBy.groupByPokemons(pokemons);
        groupByPokemonsOrderNameLength = orderBy.orderByNameLength(groupByPokemons);
        groupByPokemonsLength = groupBy.groupByNameLength(groupByPokemonsOrderNameLength);
        groupByPokemonsOrderName = orderBy.orderByName(groupByPokemons, groupByPokemonsLength);
        return result();
    }

    private List<Pokemon> result() {
        List<Pokemon> pokemons = null;
        List<Pokemon> pokemons_order_by_name = null;
        List<Pokemon> filter = new ArrayList<Pokemon>();

        for (GroupByImpl groupByPokemonNameLength : groupByPokemonsOrderNameLength) {
            pokemons = groupByPokemonNameLength.getPokemons();
            for (GroupByImpl groupByPokemonByName : groupByPokemonsOrderName) {
                if (groupByPokemonByName.getLetter().getLetter() == groupByPokemonNameLength.getLetter().getLetter()) {
                    int position_start = groupByPokemonByName.getStart();
                    int position_end = groupByPokemonByName.getEnd();
                    pokemons_order_by_name = groupByPokemonByName.getPokemons();

                    for (Pokemon pokemon_updated : pokemons_order_by_name) {
                        if (position_start <= position_end) {
                            pokemons.set(position_start, pokemon_updated);
                            position_start++;
                        }
                    }
                }
            }
            filter.addAll(pokemons);
        }

        return filter;
    }

}
