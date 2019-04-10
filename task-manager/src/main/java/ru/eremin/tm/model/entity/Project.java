package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 8875298947374839761L;

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
