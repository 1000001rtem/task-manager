package ru.eremin.tm.server.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class IncorrectDataException extends Exception {

    public IncorrectDataException(final String message) {
        super(message);
    }

    public IncorrectDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(final Throwable cause) {
        super(cause);
    }

}
