package com.looqbox.pokemon.services.exceptions;

public class ObjectNotFoundException extends  RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
