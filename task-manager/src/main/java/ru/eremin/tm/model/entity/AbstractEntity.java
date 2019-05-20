package ru.eremin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@Cacheable
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -8092174322434275698L;

    @Id
    @Nullable
    protected String id;

    @NotNull
    @Column(name = "create_date")
    protected Date createDate = new Date();

}
