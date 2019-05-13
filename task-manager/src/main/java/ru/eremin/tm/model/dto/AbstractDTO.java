package ru.eremin.tm.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.model.dto.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@XmlTransient
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1731687222717524508L;

    @NotNull
    @XmlElement(name = "id")
    protected String id = UUID.randomUUID().toString();

    @NotNull
    @XmlElement(name = "createDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
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
