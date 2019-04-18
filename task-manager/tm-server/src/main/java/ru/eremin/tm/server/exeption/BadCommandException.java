package ru.eremin.tm.server.exeption;

/**
 * @autor av.eremin on 16.04.2019.
 */

public class BadCommandException extends CommandException {

    public BadCommandException() {
    }

    public BadCommandException(final String message) {
        super(message);
    }

    public BadCommandException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BadCommandException(final Throwable cause) {
        super(cause);
    }

}
