package com.looqbox.pokemon.domain.impl;

import com.looqbox.pokemon.domain.entities.Alphabet;
import com.looqbox.pokemon.domain.entities.Letter;
import com.looqbox.pokemon.domain.entities.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.looqbox.pokemon.utils.UtilList.searchBinary;
import static com.looqbox.pokemon.utils.UtilList.searchPokemon;


@Setter
@Getter
@AllArgsConstructor
public class SwapImpl {

    private int i;
    private List<Pokemon> pokemons;
    private List<Pokemon> pokemons_order;
    private Alphabet alphabet;

    public SwapImpl(){
        this.alphabet = Alphabet.getInstance();
    }

    public SwapImpl(int i, List<Pokemon> pokemons, List<Pokemon> pokemons_order) {
        this.i = i;
        this.pokemons = pokemons;
        this.pokemons_order = pokemons_order;
        this.alphabet = Alphabet.getInstance();
    }

    public Alphabet getAlphabet(){
        if(alphabet == null){
            alphabet = Alphabet.getInstance();
        }
        return alphabet;
    }

    public boolean swap(Pokemon pokemon_current, Pokemon pokemon_swap) {

        for (int j = i + 1; j < pokemons.size(); j++) {
            Pokemon pokemon_next = pokemons.get(j);

            boolean found_pokemon_next = searchPokemon(pokemon_next, pokemons_order);

            if (!found_pokemon_next) {

                if (pokemon_next.getName().length() < pokemon_current.getName().length()) {

                    if (pokemon_swap == null) {
                        pokemon_swap = pokemon_next;
                    }
                    else if (pokemon_swap != null) {
                        if (pokemon_next.getName().length() < pokemon_swap.getName().length()) {
                            pokemon_swap = pokemon_next;
                        }
                    }
                }
            }

        }
        return insertion(pokemon_swap, pokemon_current, null);
    }
    public boolean swap(Pokemon pokemon_current, Pokemon pokemon_swap, Letter letter_swap) {

        for (int j = i + 1; j < pokemons.size(); j++) {
            Pokemon pokemon_next = pokemons.get(j);
            boolean found_pokemon_next = searchPokemon(pokemon_next, pokemons_order);
            if (!found_pokemon_next) {
                String name_pokemon_next = pokemon_next.getName();
                String name_pokemon_current = pokemon_current.getName();
                int name_pokemon_length = pokemon_current.getName().length();
                int found_position = -1;
                for (int k = 0; k < name_pokemon_length; k++) {
                    if (name_pokemon_current.charAt(k) != name_pokemon_next.charAt(k)) {
                        found_position = k;
                        break;
                    }
                }

                if (found_position > 0) {
                    Letter letter_pokemon_current = searchBinary(name_pokemon_current.charAt(found_position), getAlphabet().getLetters());
                    Letter letter_pokemon_next = searchBinary(name_pokemon_next.charAt(found_position), getAlphabet().getLetters());
                    if (letter_pokemon_next.getValue() < letter_pokemon_current.getValue()) {
                        if (pokemon_swap == null && letter_swap == null) {
                            pokemon_swap = pokemon_next;
                            letter_swap = letter_pokemon_next;
                        } else if (letter_pokemon_next.getValue() < letter_swap.getValue()) {
                            pokemon_swap = pokemon_next;
                            letter_swap = letter_pokemon_next;
                        }
                    }
                }
            }
        }
        return insertion(pokemon_swap, pokemon_current, letter_swap);
    }
    private boolean insertion( Pokemon pokemon_swap, Pokemon pokemon_current, Letter letter_swap) {
        if (pokemon_swap != null) {
            pokemons_order.add(pokemon_swap);
            if (pokemon_swap.getName().equals(pokemon_current.getName())) {
                i++;
            }

        }
        else if (pokemon_swap == null) {
            pokemons_order.add(pokemon_current);
            i++;
        }

        pokemon_swap = null;
        letter_swap = null;

        if (pokemons_order.size() == pokemons.size()) {
            return true;
        }
        return false;
    }

}
