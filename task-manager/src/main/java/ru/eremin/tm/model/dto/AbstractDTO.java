package ru.eremin.tm.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    protected Date createDate = new Date();

}
