package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -379365728203711699L;

    public ProjectDTO(@Nullable final Project project) {
        if (project == null) return;
        if (project.getId() != null && !project.getId().isEmpty()) this.id = project.getId();
        if (project.getName() != null && !project.getName().isEmpty()) this.name = project.getName();
        if (project.getDescription() != null && !project.getDescription().isEmpty()) {
            this.description = project.getDescription();
        }
        if (project.getStartDate() != null) this.startDate = project.getStartDate();
        if (project.getEndDate() != null) this.endDate = project.getEndDate();
        if (project.getUserId() != null && !project.getUserId().isEmpty()) this.userId = project.getUserId();
        if (project.getStatus() != null) this.status = project.getStatus();
        this.createDate = project.getCreateDate();
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

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
