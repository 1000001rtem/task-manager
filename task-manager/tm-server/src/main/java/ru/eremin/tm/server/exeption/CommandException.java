package ru.eremin.tm.server.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class CommandException extends Exception {

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
