package no.ntnu.idata2306.repositories;


import no.ntnu.idata2306.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
    // TODO - decide if user should be able to change password.
}
