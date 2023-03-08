package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findBySessionID(String sessionID);

    User findByName(String name);
    boolean existsBySessionID(String sessionID);
}
