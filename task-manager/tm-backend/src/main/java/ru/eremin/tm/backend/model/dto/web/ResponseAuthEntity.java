package ru.eremin.tm.backend.model.dto.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.backend.model.entity.enumerated.Role;

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
@XmlRootElement(name = "authResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAuthEntity {

    @NotNull
    @XmlElement(name = "userId")
    private String userId;

    @NotNull
    @XmlElement(name = "login")
    private String login;

    @NotNull
    @XmlElement(name = "token")
    private String token;

    @NotNull
    @XmlElement(name = "role")
    private Role role;

    public ResponseAuthEntity(@NotNull final String userId,
                              @NotNull final String login,
                              @NotNull final String token,
                              @NotNull final Role role) {
        this.userId = userId;
        this.login = login;
        this.token = token;
        this.role = role;
    }

}
