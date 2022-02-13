package com.looqbox.pokemon.utils.factory;

import com.looqbox.pokemon.domain.entities.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AlphabetFactoryTest {


    private List<Letter> alphabet;
    private List<Letter> result;
    private static final int INDEX = 0;

    @BeforeEach
    void setUp() {
        startAlphabet();
    }

    @Test
    void createAlphabet() {
        result =  AlphabetFactory.createAlphabet();
        assertNotNull(result);
        assertNotEquals(0, result.size());
        assertEquals(Letter.class, result.get(INDEX).getClass());
        assertEquals(alphabet.size(), result.size());
        assertEquals(alphabet, result);
    }

    private void startAlphabet(){
        alphabet = new ArrayList<>();
        alphabet.add(new Letter(0, 'a'));
        alphabet.add(new Letter(1, 'b'));
        alphabet.add(new Letter(2, 'c'));
        alphabet.add(new Letter(3, 'd'));
        alphabet.add(new Letter(4, 'e'));
        alphabet.add(new Letter(5, 'f'));
        alphabet.add(new Letter(6, 'g'));
        alphabet.add(new Letter(7, 'h'));
        alphabet.add(new Letter(8, 'i'));
        alphabet.add(new Letter(9, 'j'));
        alphabet.add(new Letter(10, 'k'));
        alphabet.add(new Letter(11, 'l'));
        alphabet.add(new Letter(12, 'm'));
        alphabet.add(new Letter(13, 'n'));
        alphabet.add(new Letter(14, 'o'));
        alphabet.add(new Letter(15, 'p'));
        alphabet.add(new Letter(16, 'q'));
        alphabet.add(new Letter(17, 'r'));
        alphabet.add(new Letter(18, 's'));
        alphabet.add(new Letter(19, 't'));
        alphabet.add(new Letter(20, 'u'));
        alphabet.add(new Letter(21, 'v'));
        alphabet.add(new Letter(22, 'w'));
        alphabet.add(new Letter(23, 'x'));
        alphabet.add(new Letter(24, 'y'));
        alphabet.add(new Letter(25, 'z'));
    }
}