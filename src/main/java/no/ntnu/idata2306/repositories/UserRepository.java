package no.ntnu.idata2306.repositories;


import no.ntnu.idata2306.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * repository for users.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
