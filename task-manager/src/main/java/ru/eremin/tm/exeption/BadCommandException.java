package ru.eremin.tm.exeption;

/**
 * @autor av.eremin on 16.04.2019.
 */

public class BadCommandException extends Exception {

    public BadCommandException(final String message) {
        super(message);
    }

    public BadCommandException(final Throwable cause) {
        super(cause);
    }
}
