package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.enumerated.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * @autor av.eremin on 11.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 6487760672378171712L;

    @Nullable
    protected String name;

    @Nullable
    protected String description;

    @Nullable
    protected Date startDate;

    @Nullable
    protected Date endDate;

    @NotNull
    protected Status status = Status.PLANNED;

    @Nullable
    protected String userId;

}