package ru.eremin.tm.server.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 19.04.2019.
 */

@NoArgsConstructor
public class SessionValidateExeption extends Exception {

    public SessionValidateExeption(final String message) {
        super(message);
    }

    public SessionValidateExeption(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SessionValidateExeption(final Throwable cause) {
        super(cause);
    }

}
