package com.looqbox.pokemon.utils;

import com.looqbox.pokemon.domain.entities.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilSortingTest {

    private static final int INDEX = 0;

    private List<Letter> letters_orderly;
    private List<Letter> letters_disorderly;
    private List<Letter> result;
    private Letter[] aux_letters_values;


    @BeforeEach
    void setUp() {
        startLetters();
    }

    @Test
    void quicksort() {
        UtilSorting.quicksort(aux_letters_values, 0, aux_letters_values.length -1);
        result = Arrays.asList(aux_letters_values);
        assertNotNull(result);
        assertNotEquals(0, result.size());
        assertEquals(Letter.class, result.get(INDEX).getClass());
        assertInstanceOf(Letter.class, result.get(INDEX));
        assertEquals(letters_orderly, result);
    }

    private void startLetters() {
        letters_orderly = new ArrayList<>();
        letters_disorderly = new ArrayList<>();

        letters_orderly.add(new Letter(0, 'A'));
        letters_orderly.add(new Letter(1, 'B'));
        letters_orderly.add(new Letter(2,'C'));
        letters_orderly.add(new Letter(3,'D'));
        letters_orderly.add(new Letter(4,'E'));

        letters_disorderly.add(new Letter(1,'B'));
        letters_disorderly.add(new Letter(3,'D'));
        letters_disorderly.add(new Letter(0,'A'));
        letters_disorderly.add(new Letter(4,'E'));
        letters_disorderly.add(new Letter(2,'C'));

        aux_letters_values = new Letter[letters_disorderly.size()];
        aux_letters_values = letters_disorderly.toArray(aux_letters_values);
    }

}