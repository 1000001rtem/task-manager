package ru.eremin.tm.backend.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.backend.model.entity.enumerated.Role;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor av.eremin on 11.04.2019.
 */

@Entity
@Getter
@Setter
@Cacheable
@NoArgsConstructor
@Table(name = "user_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 7036583160470114038L;

    @Nullable
    @Column(name = "user_login", nullable = false, unique = true)
    private String login;

    @Nullable
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Nullable
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> tasks = new ArrayList<>();

}
