package ru.eremin.tm.model.entity;

import org.jetbrains.annotations.NotNull;

/**
 * @autor av.eremin on 11.04.2019.
 */

public enum Role {

    ADMIN("АДМИНИСТРАТОР"),
    USER("ПОЛЬЗОВАТЕЛЬ");

    @NotNull
    private String displayName;

    Role(@NotNull final String displayName) {
        this.displayName = displayName;
    }

}
