package com.looqbox.pokemon.feignclients;

import com.looqbox.pokemon.dto.ListOfPokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static com.looqbox.pokemon.utils.UtilString.URL_POKEMON;
import static com.looqbox.pokemon.utils.UtilString.URL_POKE_API;

@FeignClient(name = "get", url =  URL_POKE_API)
public interface IPokemonFeignClient {

    @GetMapping(value = URL_POKEMON)
    ListOfPokemonDTO getAllPokemons();

}
