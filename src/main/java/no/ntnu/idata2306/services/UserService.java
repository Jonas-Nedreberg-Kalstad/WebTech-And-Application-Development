package no.ntnu.idata2306.services;

import no.ntnu.idata2306.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for User.
 *
 * @author Edvin Astad
 * @version 22.03.2023
 */
@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
