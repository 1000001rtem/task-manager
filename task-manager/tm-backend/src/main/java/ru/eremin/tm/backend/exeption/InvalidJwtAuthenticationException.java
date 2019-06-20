package ru.eremin.tm.backend.exeption;

/**
 * @autor av.eremin on 30.05.2019.
 */

public class InvalidJwtAuthenticationException extends Exception {

    public InvalidJwtAuthenticationException(final String message) {
        super(message);
    }

}
