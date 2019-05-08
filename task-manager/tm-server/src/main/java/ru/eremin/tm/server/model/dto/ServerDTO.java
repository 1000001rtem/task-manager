package ru.eremin.tm.server.model.dto;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autor av.eremin on 08.05.2019.
 */

@XmlRootElement
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerDTO {

    @Nullable
    @XmlElement(name = "host")
    private String host;

    @Nullable
    @XmlElement(name = "port")
    private String port;

    public ServerDTO(@Nullable final String host, @Nullable final String port) {
        this.host = host;
        this.port = port;
    }

}
