package ru.eremin.tm.client.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autor av.eremin on 31.05.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "loginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSoapEntity {

    @NotNull
    @XmlElement(name = "login")
    private String login;

    @NotNull
    @XmlElement(name = "token")
    private String token;

    public ResponseSoapEntity(@NotNull final String login, @NotNull final String token) {
        this.login = login;
        this.token = token;
    }

}
