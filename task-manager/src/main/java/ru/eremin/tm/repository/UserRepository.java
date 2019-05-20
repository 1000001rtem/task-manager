package ru.eremin.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.model.entity.User;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository(UserRepository.NAME)
public interface UserRepository extends JpaRepository<User, String> {

    String NAME = "userRepository";

    @Query(value = "SELECT e FROM User e WHERE e.login = ?1")
    User findByLogin(String login);

}
