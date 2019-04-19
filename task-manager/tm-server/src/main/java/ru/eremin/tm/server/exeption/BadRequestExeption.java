package ru.eremin.tm.server.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 19.04.2019.
 */

@NoArgsConstructor
public class BadRequestExeption extends Exception {

    public BadRequestExeption(final String message) {
        super(message);
    }

    public BadRequestExeption(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BadRequestExeption(final Throwable cause) {
        super(cause);
    }
}
