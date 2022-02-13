package com.looqbox.pokemon.services.impl;

import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.feignclients.IPokemonFeignClient;
import com.looqbox.pokemon.services.exceptions.ObjectNotFoundException;
import com.looqbox.pokemon.utils.UtilString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.looqbox.pokemon.utils.UtilString.NAME_POKEMON;
import static com.looqbox.pokemon.utils.UtilString.NAME_POKEMON_SEARCH;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PokemonServiceImplTest {

    private static final int INDEX = 0;
    @Autowired
    private PokemonServiceImpl pokemonService;
    @Autowired
    private IPokemonFeignClient pokemonFeignClient;

    @Test
    void whenFindByPokemonReturnObjectNotFoundException(){
        try {
            pokemonService.getPokemon("12312");
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(UtilString.MESSAGE_NOT_FOUND_POKEMON, ex.getMessage());
        }
    }

    @Test
    void whenFindByNameThenReturnAnListOfPokemons(){
        List<Pokemon> pokemons = pokemonService.getPokemon(NAME_POKEMON_SEARCH);

        assertNotNull(pokemons);
        assertNotEquals(0, pokemons.size());
        assertEquals(4, pokemons.size());
        assertEquals(Pokemon.class, pokemons.get(INDEX).getClass());
        assertEquals(NAME_POKEMON, pokemons.get(INDEX).getName());
    }

    @Test
    void whenFindAllThenReturnAnListOfPokemons(){
        List<Pokemon> pokemons = pokemonService.getAllPokemons();

        assertNotNull(pokemons);
        assertNotEquals(0, pokemons.size());
        assertEquals(20, pokemons.size());
        assertEquals(Pokemon.class, pokemons.get(INDEX).getClass());
        assertEquals(NAME_POKEMON, pokemons.get(INDEX).getName());
    }

}