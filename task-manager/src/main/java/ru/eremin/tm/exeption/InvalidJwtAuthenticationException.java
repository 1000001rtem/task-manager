package ru.eremin.tm.exeption;

/**
 * @autor av.eremin on 30.05.2019.
 */

public class InvalidJwtAuthenticationException extends Exception {

    public InvalidJwtAuthenticationException(final String message) {
        super(message);
    }
}
