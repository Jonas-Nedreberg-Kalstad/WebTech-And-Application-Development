package no.ntnu.idata2306.repositories;

import java.util.Optional;
import no.ntnu.idata2306.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for users.
 *
 * @author Edvin Astad
 * @version 17.03.2023
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

  /**
   * Retrieves a user from the database based on the provided email address.
   *
   * @param email The email address of the user.
   * @return An Optional containing the user with the given email address, or empty if no user is found.
   */
  Optional<User> findByEmail(String email);
}
