package ru.eremin.tm.client.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.client.model.dto.enumerated.Status;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    protected Date startDate;

    @Nullable
    @XmlElement(name = "endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    protected Date endDate;

    @NotNull
    @XmlElement(name = "status")
    protected Status status = Status.PLANNED;

    @Nullable
    @XmlElement(name = "userId")
    protected String userId;

}
