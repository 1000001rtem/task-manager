package ru.eremin.tm.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autor av.eremin on 19.04.2019.
 */

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultDTO {

    private boolean result;

    @Nullable
    @XmlElement(name = "message")
    private String message;

    @Nullable
    @XmlElement(name = "exception")
    private Exception exception;

    public ResultDTO(final boolean result) {
        this.result = result;
    }

    public ResultDTO(@Nullable final Exception exception) {
        if (exception == null) return;
        this.result = false;
        this.message = exception.getMessage();
        this.exception = exception;
    }

    public ResultDTO(final boolean result, @Nullable final String message) {
        this.result = result;
        this.message = message;
    }

}
