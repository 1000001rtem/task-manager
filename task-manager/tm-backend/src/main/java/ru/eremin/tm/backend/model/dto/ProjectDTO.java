package ru.eremin.tm.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.backend.model.entity.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(propOrder = {"id", "name", "description", "createDate", "startDate", "endDate", "status", "userId"})
@JsonPropertyOrder({"id", "name", "description", "createDate", "startDate", "endDate", "status", "userId"})
public class ProjectDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -379365728203711699L;

    public ProjectDTO(@Nullable final Project project) {
        if (project == null) return;
        if (project.getId() != null && !project.getId().isEmpty()) this.id = project.getId();
        if (project.getCreateDate() != null) this.createDate = project.getCreateDate();
        if (project.getName() != null && !project.getName().isEmpty()) this.name = project.getName();
        if (project.getDescription() != null && !project.getDescription().isEmpty()) {
            this.description = project.getDescription();
        }
        if (project.getStartDate() != null) this.startDate = project.getStartDate();
        if (project.getEndDate() != null) this.endDate = project.getEndDate();
        if (project.getUser() != null) this.userId = project.getUser().getId();
        if (project.getStatus() != null) this.status = project.getStatus();
        this.createDate = project.getCreateDate();
    }

    public ProjectDTO(@Nullable final ProjectDTO projectDTO) {
        if (projectDTO == null) return;
        if (projectDTO.getCreateDate() != null) this.createDate = projectDTO.getCreateDate();
        if (projectDTO.getName() != null && !projectDTO.getName().isEmpty()) this.name = projectDTO.getName();
        if (projectDTO.getDescription() != null && !projectDTO.getDescription().isEmpty()) {
            this.description = projectDTO.getDescription();
        }
        if (projectDTO.getStartDate() != null) this.startDate = projectDTO.getStartDate();
        if (projectDTO.getEndDate() != null) this.endDate = projectDTO.getEndDate();
        if (projectDTO.getStatus() != null) this.status = projectDTO.getStatus();
    }

    public String info() {
        return name +
                "{id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
