package com.looqbox.pokemon.exceptions;

import com.looqbox.pokemon.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.time.LocalDateTime;

import static com.looqbox.pokemon.utils.UtilString.MESSAGE_NOT_FOUND_POKEMON;
import static com.looqbox.pokemon.utils.UtilString.MESSAGE_UNDEFINED_PARAMETERS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {


    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void objectNotFound(){
        ResponseEntity<StandardError> response = exceptionHandler.
                objectNotFound(new ObjectNotFoundException(MESSAGE_NOT_FOUND_POKEMON)
                        , new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(MESSAGE_NOT_FOUND_POKEMON, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/pokemon", response.getBody().getPath());
        assertEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void undefinedParameters(){
        ResponseEntity<StandardError> response = exceptionHandler.
                undefinedParameters(new MissingServletRequestParameterException("","")
                        , new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(MESSAGE_UNDEFINED_PARAMETERS, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
        assertNotEquals("/pokemon", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());

    }
}