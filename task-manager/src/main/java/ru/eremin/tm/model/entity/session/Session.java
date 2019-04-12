package ru.eremin.tm.model.entity.session;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.base.AbstractEntity;

import java.util.UUID;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class Session extends AbstractEntity {

    @Nullable
    private UserDTO user;

    public Session(@Nullable final UserDTO user) {
        if(user == null) return;
        this.id = UUID.randomUUID().toString();
        this.user = user;
    }

}
