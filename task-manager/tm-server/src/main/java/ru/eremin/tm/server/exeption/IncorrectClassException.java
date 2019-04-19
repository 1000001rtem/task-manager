package ru.eremin.tm.server.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 15.04.2019.
 */

@NoArgsConstructor
public class IncorrectClassException extends CommandException {

    public IncorrectClassException(final String message) {
        super(message);
    }

    public IncorrectClassException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IncorrectClassException(final Throwable cause) {
        super(cause);
    }

}
