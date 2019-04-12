package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.base.BaseDTO;
import ru.eremin.tm.model.entity.Task;

import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8634181313297237339L;

    @Nullable
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
    }

    public String info() {
        return name +
                "{id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
