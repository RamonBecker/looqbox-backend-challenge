package com.looqbox.pokemon.utils;

import com.looqbox.pokemon.domain.entities.Letter;

public class UtilSorting {
    public static void quicksort(Letter[] vector, int left, int right) {
        if (left < right) {
            int p = partition(vector, left, right);
            quicksort(vector, left, p);
            quicksort(vector, p + 1, right);
        }
    }
    private static int partition(Letter[] vector, int left, int right) {
        int meio = (int) (left + right) / 2;
        int pivot = vector[meio].getValue();
        int i = left - 1;
        int j = right + 1;
        while (true) {
            do {
                i++;
            } while (vector[i].getValue() < pivot);
            do {
                j--;
            } while (vector[j].getValue() > pivot);
            if (i >= j) {
                return j;
            }
            Letter aux = vector[i];
            vector[i] = vector[j];
            vector[j] = aux;
        }
    }
}
