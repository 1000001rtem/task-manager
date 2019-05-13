package ru.eremin.tm.model.entity.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.AbstractEntity;
import ru.eremin.tm.model.entity.enumerated.Role;

import java.util.UUID;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class Session extends AbstractEntity {

    @Nullable
    private String userId;

    @Nullable
    private Role userRole;

    @Nullable
    private String sign;

    public Session(@Nullable final UserDTO user) {
        if (user == null) return;
        this.id = UUID.randomUUID().toString();
        this.userId = user.getId();
        this.userRole = user.getRole();
    }

}
