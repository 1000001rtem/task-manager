package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 8875298947374839761L;

    private String name;

    private Date deadline;

    public Project(final String name, final Date deadline) {
        this.name = name;
        this.deadline = new Date(deadline.getTime());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                '}';
    }

}
