package ru.eremin.tm.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlElement;

/**
 * @autor av.eremin on 19.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class ResultDTO {

    private boolean result;

    @Nullable
    @XmlElement(name = "message")
    private String message;

    @Nullable
    @XmlElement(name = "exception")
    private Exception e;

    public ResultDTO(final boolean result) {
        this.result = result;
    }

    public ResultDTO(@Nullable final Exception e) {
        if (e == null) return;
        this.result = false;
        this.message = e.getMessage();
        this.e = e;
    }

    public ResultDTO(final boolean result, @Nullable final String message) {
        this.result = result;
        this.message = message;
    }

}
