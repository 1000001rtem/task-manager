package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -8092174322434275698L;

    @Nullable
    protected String id;

    @Nullable
    protected String name;

    @Nullable
    protected String description;

    @Nullable
    protected Date startDate;

    @Nullable
    protected Date endDate;

}
