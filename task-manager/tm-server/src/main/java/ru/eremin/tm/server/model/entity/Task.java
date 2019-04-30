package ru.eremin.tm.server.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task_table")
public class Task extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -575064935487180132L;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", project='" + project + '\'' +
                '}';
    }

}
