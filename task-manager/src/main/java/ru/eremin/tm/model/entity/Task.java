package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -575064935487180132L;

    @Nullable
    private String projectId;

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectId='" + projectId + '\'' +
                '}';
    }

}
