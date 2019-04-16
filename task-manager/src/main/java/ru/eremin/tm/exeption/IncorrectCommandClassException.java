package ru.eremin.tm.exeption;

/**
 * @autor av.eremin on 15.04.2019.
 */

public class IncorrectCommandClassException extends RuntimeException {

    public IncorrectCommandClassException(final String message) {
        super(message);
    }

    public IncorrectCommandClassException(final Throwable cause) {
        super(cause);
    }
}
