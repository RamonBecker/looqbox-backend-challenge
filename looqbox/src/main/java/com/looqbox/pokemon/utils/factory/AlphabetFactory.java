package com.looqbox.pokemon.utils.factory;

import com.looqbox.pokemon.domain.entities.Letter;

import java.util.ArrayList;
import java.util.List;

public class AlphabetFactory {

    public static List<Letter> createAlphabet(){
        char aux_letra;
        int i = 0;
        List<Letter> list = new ArrayList<Letter>();
        for (aux_letra = 'a'; aux_letra <= 'z'; aux_letra++) {
            list.add(new Letter(i, aux_letra));
            i++;
        }
        return list;
    }
}
