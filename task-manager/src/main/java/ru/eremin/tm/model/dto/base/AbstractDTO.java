package ru.eremin.tm.model.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1731687222717524508L;

    @NotNull
    protected String id = UUID.randomUUID().toString();

    @NotNull
    protected Date createDate = new Date();

}
