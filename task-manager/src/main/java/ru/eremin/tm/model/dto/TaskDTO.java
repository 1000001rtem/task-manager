package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eremin.tm.model.entity.Task;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = -8634181313297237339L;

    private String name;

    private Date deadline;

    private String projectId;

    public TaskDTO(final Task task) {
        if (task == null) return;
        if (task.getId() != null && !task.getId().isEmpty()) this.id = task.getId();
        if (task.getName() != null && !task.getName().isEmpty()) this.name = task.getName();
        if (task.getDeadline() != null) this.deadline = task.getDeadline();
        if (task.getProjectId() != null && !task.getProjectId().isEmpty()) this.projectId = task.getProjectId();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDTO)) return false;
        if (!super.equals(o)) return false;
        final TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
