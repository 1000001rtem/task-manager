package ru.eremin.tm.server.model.entity.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.AbstractEntity;
import ru.eremin.tm.server.model.entity.enumerated.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Session extends AbstractEntity {

    @Nullable
    @Column(name = "user_id")
    private String userId;

    @Nullable
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    @Nullable
    @Column(name = "sign")
    private String sign;

    public Session(@Nullable final UserDTO user) {
        if (user == null) return;
        this.id = UUID.randomUUID().toString();
        this.userId = user.getId();
        this.userRole = user.getRole();
    }

}
