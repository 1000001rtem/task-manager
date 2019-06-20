package ru.eremin.tm.backend.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Entity
@Getter
@Setter
@Cacheable
@NoArgsConstructor
@Table(name = "task_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -575064935487180132L;

    @ManyToOne
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
                ", projectId='" + project.getId() + '\'' +
                '}';
    }

}
