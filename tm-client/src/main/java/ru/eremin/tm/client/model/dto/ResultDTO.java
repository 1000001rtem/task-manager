package ru.eremin.tm.client.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @autor av.eremin on 27.05.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO implements Serializable {

    private static final long serialVersionUID = 258678606242723926L;

    private boolean success;

    public ResultDTO(final boolean success) {
        this.success = success;
    }

}
