package ru.eremin.tm.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class BadCommandException extends CommandException {

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
