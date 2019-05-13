package ru.eremin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.model.entity.session.Session;

/**
 * @autor av.eremin on 22.04.2019.
 */

public interface ISessionRepository extends IRepository<Session> {

    Session findByUserId(@NotNull String userId);

    void removeAll();

}
