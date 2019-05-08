package ru.eremin.tm.server.repository;

import org.apache.deltaspike.data.api.*;
import ru.eremin.tm.server.model.entity.Session;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository
public interface SessionRepository extends EntityRepository<Session, String> {

    @Query(value = "SELECT e FROM Session e WHERE e.userId = ?1", singleResult = SingleResultType.OPTIONAL)
    Session findByUserId(String id);

    @Modifying
    @Query("DELETE FROM Session e")
    void removeAll();

}
