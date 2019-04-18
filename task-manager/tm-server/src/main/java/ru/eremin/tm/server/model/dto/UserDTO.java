package ru.eremin.tm.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.model.entity.enumerated.Role;

import java.io.Serializable;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 4341016265655139392L;

    @Nullable
    private String login;

    @Nullable
    private String hashPassword;

    @Nullable
    private Role role;

    public UserDTO(@Nullable final User user) {
        if (user == null) return;
        this.id = user.getId();
        if (user.getLogin() != null && !user.getLogin().isEmpty()) this.login = user.getLogin();
        if (user.getHashPassword() != null && !user.getHashPassword().isEmpty()) {
            this.hashPassword = user.getHashPassword();
        }
        if (user.getRole() != null) this.role = user.getRole();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", role=" + role +
                ", id='" + id + '\'' +
                '}';
    }

}
