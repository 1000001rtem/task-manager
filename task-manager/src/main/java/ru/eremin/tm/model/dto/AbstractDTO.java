package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    protected String name;

    @Nullable
    protected String description;

    @Nullable
    protected Date startDate;

    @Nullable
    protected Date endDate;

}
