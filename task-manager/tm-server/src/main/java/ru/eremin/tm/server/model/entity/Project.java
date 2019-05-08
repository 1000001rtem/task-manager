package ru.eremin.tm.server.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Entity
@Getter
@Setter
@Cacheable
@NoArgsConstructor
@Table(name = "project_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8875298947374839761L;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
