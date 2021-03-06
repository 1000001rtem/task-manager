package ru.eremin.tm.model.entity.enumerated;

import org.jetbrains.annotations.NotNull;

/**
 * @autor av.eremin on 11.04.2019.
 */

public enum Role {

    ADMIN("ADMIN"),
    USER("USER");

    @NotNull
    private String displayName;

    Role(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
