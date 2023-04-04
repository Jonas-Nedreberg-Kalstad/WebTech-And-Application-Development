package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * repositories for roles.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
