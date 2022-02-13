package com.looqbox.pokemon.services.impl;

import com.looqbox.pokemon.domain.entities.Pokemon;
import com.looqbox.pokemon.dto.response.PokemonDTOResponse;
import com.looqbox.pokemon.feignclients.IPokemonFeignClient;
import com.looqbox.pokemon.services.IPokemonService;
import com.looqbox.pokemon.services.exceptions.ObjectNotFoundException;
import com.looqbox.pokemon.domain.IListOf;
import com.looqbox.pokemon.domain.impl.ListOfImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.looqbox.pokemon.utils.UtilString.MESSAGE_NOT_FOUND_POKEMON;


@Service
public class PokemonServiceImpl implements IPokemonService {

    @Autowired
    private IPokemonFeignClient pokemonFeignClient;
    @Autowired
    private ModelMapper mapper;

    private IListOf listOf = new ListOfImpl();

    @Override
    public List<Pokemon> getPokemon(String name) {
        List<Pokemon> allPokemons = getAllPokemons();
        if(name.trim().isEmpty()) {
            return allPokemons;
        }
        List<Pokemon> filterPokemons =  listOf.contains(name, allPokemons);
        if(filterPokemons.size() == 0){
            throw new ObjectNotFoundException(MESSAGE_NOT_FOUND_POKEMON);
        }
        return filterPokemons;
    }

    @Override
    public List<Pokemon> getAllPokemons(){
       List<PokemonDTOResponse> listDTO =   pokemonFeignClient.getAllPokemons().getResults();
       List<Pokemon> pokemons = listDTO.stream().map(x -> mapper.map(x , Pokemon.class)).collect(Collectors.toList());
       return listOf.orderByPokemon(pokemons);
    }
}
