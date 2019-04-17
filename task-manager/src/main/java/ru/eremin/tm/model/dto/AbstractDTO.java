package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDTO)) return false;
        final AbstractDTO that = (AbstractDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
