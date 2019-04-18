package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Task;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "description", "createDate", "startDate", "endDate", "status", "userId", "projectId"})
public class TaskDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8634181313297237339L;

    @Nullable
    @XmlElement(name = "projectId")
    private String projectId;

    public TaskDTO(@Nullable final Task task) {
        if (task == null) return;
        if (task.getId() != null && !task.getId().isEmpty()) this.id = task.getId();
        if (task.getName() != null && !task.getName().isEmpty()) this.name = task.getName();
        if (task.getDescription() != null && !task.getDescription().isEmpty()) this.description = task.getDescription();
        if (task.getStartDate() != null) this.startDate = task.getStartDate();
        if (task.getEndDate() != null) this.endDate = task.getEndDate();
        if (task.getProjectId() != null && !task.getProjectId().isEmpty()) this.projectId = task.getProjectId();
        if (task.getUserId() != null && !task.getUserId().isEmpty()) this.userId = task.getUserId();
        if (task.getStatus() != null) this.status = task.getStatus();
        this.createDate = task.getCreateDate();
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
