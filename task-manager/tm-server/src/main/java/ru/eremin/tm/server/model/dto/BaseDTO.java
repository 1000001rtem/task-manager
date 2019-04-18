package ru.eremin.tm.server.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.dto.adapter.DateAdapter;
import ru.eremin.tm.server.model.entity.enumerated.Status;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

/**
 * @autor av.eremin on 11.04.2019.
 */

@Getter
@Setter
@XmlTransient
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 6487760672378171712L;

    @Nullable
    @XmlElement(name = "name")
    protected String name;

    @Nullable
    @XmlElement(name = "description")
    protected String description;

    @Nullable
    @XmlElement(name = "startDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    protected Date startDate;

    @Nullable
    @XmlElement(name = "endDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    protected Date endDate;

    @NotNull
    @XmlElement(name = "status")
    protected Status status = Status.PLANNED;

    @Nullable
    @XmlElement(name = "userId")
    protected String userId;

}
