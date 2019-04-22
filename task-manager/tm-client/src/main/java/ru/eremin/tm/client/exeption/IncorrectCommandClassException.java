package ru.eremin.tm.client.exeption;

/**
 * @autor av.eremin on 15.04.2019.
 */

public class IncorrectCommandClassException extends CommandException {

    public IncorrectCommandClassException() {
    }

    public IncorrectCommandClassException(final String message) {
        super(message);
    }

    public IncorrectCommandClassException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IncorrectCommandClassException(final Throwable cause) {
        super(cause);
    }

}
