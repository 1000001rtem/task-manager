package ru.eremin.tm.server.exeption;

import lombok.NoArgsConstructor;

/**
 * @autor av.eremin on 19.04.2019.
 */

@NoArgsConstructor
public class AccessForbiddenException extends Exception {

    public AccessForbiddenException(final String message) {
        super(message);
    }

    public AccessForbiddenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(final Throwable cause) {
        super(cause);
    }

}
