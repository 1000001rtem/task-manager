package ru.eremin.tm.client.exeption;

/**
 * @autor av.eremin on 16.04.2019.
 */

public class CommandException extends Exception {

    public CommandException() {
    }

    public CommandException(final String message) {
        super(message);
    }

    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CommandException(final Throwable cause) {
        super(cause);
    }

}
