package com.looqbox.pokemon.domain.entities;

import com.looqbox.pokemon.utils.factory.AlphabetFactory;

import java.util.List;

public class Alphabet {

    private List<Letter> letters;
    private static Alphabet alphabet;

    private Alphabet(){
    }
    public static synchronized Alphabet getInstance() {
        if(alphabet == null){
            alphabet = new Alphabet();
        }
        return alphabet;
    }
    public List<Letter> getLetters(){
        if(letters == null){
            letters = AlphabetFactory.createAlphabet();
        }
        return letters;
    }
}
