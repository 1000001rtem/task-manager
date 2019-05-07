package ru.eremin.tm.server.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;
import ru.eremin.tm.server.model.entity.User;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository
public interface UserRepository extends EntityRepository<User, String> {

    @Query(value = "SELECT e FROM User e WHERE e.login = ?1", singleResult = SingleResultType.OPTIONAL)
    User findByLogin(String login);

}
