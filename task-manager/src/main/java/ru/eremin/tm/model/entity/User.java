package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.model.entity.base.AbstractEntity;

import java.io.Serializable;

/**
 * @autor av.eremin on 11.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 7036583160470114038L;

    @NotNull
    private String login;

    @NotNull
    private String hashPassword;

    @NotNull
    private Role role;

}
