package ru.eremin.tm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(propOrder = {"id", "name", "description", "createDate", "startDate", "endDate", "status", "userId", "projectId"})
@JsonPropertyOrder({"id", "name", "description", "createDate", "startDate", "endDate", "status", "userId", "projectId"})
public class TaskDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8634181313297237339L;

    @Nullable
    @XmlElement(name = "projectId")
    private String projectId;

    public TaskDTO(@Nullable final Task task) {
        if (task == null) return;
        if (task.getId() != null && !task.getId().isEmpty()) this.id = task.getId();
        if (task.getCreateDate() != null) this.createDate = task.getCreateDate();
        if (task.getName() != null && !task.getName().isEmpty()) this.name = task.getName();
        if (task.getDescription() != null && !task.getDescription().isEmpty()) this.description = task.getDescription();
        if (task.getStartDate() != null) this.startDate = task.getStartDate();
        if (task.getEndDate() != null) this.endDate = task.getEndDate();
        if (task.getProject() != null) this.projectId = task.getProject().getId();
        if (task.getUser() != null) this.userId = task.getUser().getId();
        if (task.getStatus() != null) this.status = task.getStatus();
        this.createDate = task.getCreateDate();
    }

    public TaskDTO(@Nullable final TaskDTO taskDTO) {
        if (taskDTO == null) return;
        if (taskDTO.getCreateDate() != null) this.createDate = taskDTO.getCreateDate();
        if (taskDTO.getProjectId() == null) return;
        this.projectId = taskDTO.getProjectId();
        if (taskDTO.getName() != null && !taskDTO.getName().isEmpty()) this.name = taskDTO.getName();
        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isEmpty()) {
            this.description = taskDTO.getDescription();
        }
        if (taskDTO.getStartDate() != null) this.startDate = taskDTO.getStartDate();
        if (taskDTO.getEndDate() != null) this.endDate = taskDTO.getEndDate();
        if (taskDTO.getStatus() != null) this.status = taskDTO.getStatus();

    }

    public String info() {
        return name +
                "{id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
