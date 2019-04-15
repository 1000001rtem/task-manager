package ru.eremin.tm.exeption;

/**
 * @autor av.eremin on 15.04.2019.
 */

public class IncorrectCommandException extends RuntimeException {

    public IncorrectCommandException(final String s) {
        super(s);
    }

    public IncorrectCommandException(final Exception e) {
        super(e);
    }

}
