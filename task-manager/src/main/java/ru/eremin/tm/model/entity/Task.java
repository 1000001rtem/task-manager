package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -575064935487180132L;

    private String name;

    private Date deadline;

    private String projectId;

    public Task(final String name, final Date deadline, final String projectId) {
        this.name = name;
        this.deadline = new Date(deadline.getTime());
        this.projectId = projectId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
