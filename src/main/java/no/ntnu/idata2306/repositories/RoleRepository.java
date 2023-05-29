package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * repositories for roles.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

  /**
   * Retrieves a role from the database based on the provided role name.
   *
   * @param roleName The name of the role.
   * @return An Optional containing the role with the given name, or empty if no role is found.
   */
  Optional<Role> findByName(String roleName);

}
