package com.looqbox.pokemon.domain.impl;

import com.looqbox.pokemon.domain.IGroupBy;
import com.looqbox.pokemon.domain.entities.Alphabet;
import com.looqbox.pokemon.domain.entities.Letter;
import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.utils.UtilList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.looqbox.pokemon.utils.UtilList.searchBinary;
import static com.looqbox.pokemon.utils.UtilSorting.quicksort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupByImpl implements IGroupBy {

    private int start;
    private int end;
    private Letter letter;
    private List<Pokemon> pokemons;
    private Alphabet alphabet;

    public GroupByImpl(Letter letter){
        this.letter = letter;
        this.alphabet = Alphabet.getInstance();
    }
    public GroupByImpl(Letter letter, List<Pokemon> pokemons_order) {
        this.letter = letter;
        this.pokemons = pokemons_order;
        this.alphabet = Alphabet.getInstance();
    }
    public GroupByImpl(Letter letter, int start, int end, List<Pokemon> pokemons) {
        this.letter = letter;
        this.pokemons = pokemons;
        this.start = start;
        this.end = end;
        this.alphabet = Alphabet.getInstance();
    }

    public Alphabet getAlphabet(){
        if(alphabet == null){
            alphabet = Alphabet.getInstance();
        }
        return alphabet;
    }

    public List<Pokemon> getPokemons(){
        if(pokemons == null){
            pokemons = new ArrayList<>();
        }
        return pokemons;
    }

    @Override
    public List<GroupByImpl> groupByPokemons(List<Pokemon> pokemons) {
        int cont = 0;
        Letter aux_letter = null;
        String attribute = null;
        Letter[] aux_letters_values = null;

        List<Letter> letters = new ArrayList<Letter>();
        List<GroupByImpl> filter = new ArrayList<GroupByImpl>();

        for (Pokemon pokemon : pokemons) {
            attribute = pokemon.getName().trim().toLowerCase();
            aux_letter = searchBinary(attribute.charAt(0), getAlphabet().getLetters());
            if (attribute != null) {
                if (!(attribute.isEmpty())) {
                    if (aux_letter != null) {
                        for (Letter letra : letters) {
                            if (letra.getLetter() != attribute.charAt(0)) {
                                cont++;
                            }
                        }

                        if (cont == letters.size()) {
                            letters.add(aux_letter);
                        }
                        cont = 0;
                        aux_letter = null;
                    }
                }
            }
            aux_letter = null;
            attribute = null;
        }

        if (letters != null) {
            aux_letters_values = new Letter[letters.size()];
            aux_letters_values = letters.toArray(aux_letters_values);
            quicksort(aux_letters_values, 0, aux_letters_values.length - 1);
            letters = Arrays.asList(aux_letters_values);
        }

        for (Letter letra : letters) {
            filter.add(new GroupByImpl(letra));
        }

        for (GroupByImpl groupByPokemon : filter) {
            for (Pokemon pokemon : pokemons) {
                attribute = pokemon.getName().trim().toLowerCase();
                if (attribute != null) {
                    if (!(attribute.isEmpty())) {
                        if (attribute.charAt(0) == groupByPokemon.getLetter().getLetter()) {
                            groupByPokemon.getPokemons().add(pokemon);
                        }
                    }
                }
                attribute = null;
            }
        }
        return filter;
    }
    @Override
    public List<GroupByImpl> groupByNameLength(List<GroupByImpl> groupByPokemons) {
        boolean found_pokemon_length_repeated = false;
        GroupByImpl filterGroupByPokemonNameLength = null;
        List<GroupByImpl> filter = new ArrayList<GroupByImpl>();

        for (GroupByImpl groupByPokemon : groupByPokemons) {
            List<Pokemon> pokemons = groupByPokemon.getPokemons();

            for (int i = 0; i < pokemons.size(); i++) {
                Pokemon pokemon_current = pokemons.get(i);

                boolean pokemon_inserted = false;

                for (GroupByImpl groupByPokemonLength : filter) {
                    if (groupByPokemonLength.getLetter().equals(groupByPokemon.getLetter())) {
                        pokemon_inserted = UtilList.searchPokemon(pokemon_current, groupByPokemonLength.getPokemons());
                    }
                }

                if (!pokemon_inserted) {
                    for (int j = i + 1; j < pokemons.size(); j++) {
                        Pokemon pokemon_next = pokemons.get(j);
                        if (pokemon_current.getName().length() == pokemon_next.getName().length()) {
                            found_pokemon_length_repeated = true;
                            break;
                        }
                    }
                    if (found_pokemon_length_repeated) {
                        filterGroupByPokemonNameLength = new GroupByImpl(groupByPokemon.getLetter());
                        filterGroupByPokemonNameLength.getPokemons().add(pokemon_current);
                        filterGroupByPokemonNameLength.setStart(i);

                        for (int j = i + 1; j < pokemons.size(); j++) {
                            Pokemon pokemon_next = pokemons.get(j);
                            if (pokemon_current.getName().length() == pokemon_next.getName().length()) {
                                filterGroupByPokemonNameLength.getPokemons().add(pokemon_next);
                                filterGroupByPokemonNameLength.setEnd(j);
                            }
                            if (pokemon_current.getName().length() != pokemon_next.getName().length()) {
                                break;
                            }
                        }
                        found_pokemon_length_repeated = false;
                        filter.add(filterGroupByPokemonNameLength);
                    }
                }
            }
        }
        return filter;
    }
}
