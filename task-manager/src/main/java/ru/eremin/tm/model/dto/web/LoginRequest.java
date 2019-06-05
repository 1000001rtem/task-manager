package ru.eremin.tm.model.dto.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @autor av.eremin on 31.05.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "loginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 7988702036271665504L;

    @NotNull
    @XmlElement(name = "login")
    private String login;

    @NotNull
    @XmlElement(name = "password")
    private String password;

    public LoginRequest(@NotNull final String login, @NotNull final String password) {
        this.login = login;
        this.password = password;
    }

}
