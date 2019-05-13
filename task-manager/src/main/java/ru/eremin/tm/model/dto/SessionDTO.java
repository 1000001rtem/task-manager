package ru.eremin.tm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.model.entity.session.Session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autor av.eremin on 22.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "session")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO extends AbstractDTO {

    @Nullable
    @XmlElement(name = "userId")
    private String userId;

    @Nullable
    @XmlElement(name = "userRole")
    private Role userRole;

    @Nullable
    @XmlElement(name = "sign")
    private String sign;

    public SessionDTO(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        this.userId = userDTO.getId();
        this.userRole = userDTO.getRole();
    }

    public SessionDTO(@Nullable final Session session) {
        if (session == null) return;
        if (session.getId() != null && !session.getId().isEmpty()) this.id = session.getId();
        if (session.getUserId() != null && !session.getUserId().isEmpty()) this.userId = session.getUserId();
        if (session.getUserRole() != null) this.userRole = session.getUserRole();
        if (session.getSign() != null && !session.getSign().isEmpty()) this.sign = session.getSign();
    }

    public SessionDTO(@Nullable final SessionDTO sessionDTO) {
        if (sessionDTO == null) return;
        if (sessionDTO.getUserId() != null && !sessionDTO.getUserId().isEmpty()) this.userId = sessionDTO.getUserId();
        if (sessionDTO.getUserRole() != null) this.userRole = sessionDTO.getUserRole();
        if (sessionDTO.getSign() != null && !sessionDTO.getSign().isEmpty()) this.sign = sessionDTO.getSign();
    }

    @Override
    public String toString() {
        return "SessionDTO{" +
                "userId='" + userId + '\'' +
                ", userRole=" + userRole +
                ", sign='" + sign + '\'' +
                ", id='" + id + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
