package ru.eremin.tm.server.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.enumerated.Status;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @autor av.eremin on 11.04.2019.
 */

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -8258236348117530843L;

    @Nullable
    @Column(name = "name")
    protected String name;

    @Nullable
    @Column(name = "description")
    protected String description;

    @Nullable
    @Column(name = "start_date")
    protected Date startDate;

    @Nullable
    @Column(name = "end_date")
    protected Date endDate;

    @Nullable
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    protected Status status;

    @Column(name = "user_id")
    protected User user;

}
