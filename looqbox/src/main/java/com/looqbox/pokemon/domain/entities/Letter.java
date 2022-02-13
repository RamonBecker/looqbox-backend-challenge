package com.looqbox.pokemon.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Letter {
    private Integer value;
    private Character letter;

    public Letter(Character letter) {
        this.letter = letter;
    }
}
