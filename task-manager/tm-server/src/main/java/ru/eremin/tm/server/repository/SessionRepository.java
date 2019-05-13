package ru.eremin.tm.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.server.model.entity.Session;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository(SessionRepository.NAME)
public interface SessionRepository extends JpaRepository<Session, String> {

    String NAME = "sessionRepository";

    @Query(value = "SELECT e FROM Session e WHERE e.userId = ?1")
    Session findByUserId(String id);

    @Modifying
    @Query("DELETE FROM Session e")
    void removeAll();

}
