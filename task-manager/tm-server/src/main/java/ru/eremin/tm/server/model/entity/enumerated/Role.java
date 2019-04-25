package ru.eremin.tm.server.model.entity.enumerated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @autor av.eremin on 11.04.2019.
 */

public enum Role {

    ADMIN("admin"),
    USER("user");

    @NotNull
    private String displayName;

    Role(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    @Nullable
    public static Role getByDisplayName(@Nullable final String displayName) {
        for (final Role role : values()) {
            if (role.displayName.equalsIgnoreCase(displayName)) return role;
        }
        return null;
    }


    @Override
    public String toString() {
        return displayName;
    }

}
