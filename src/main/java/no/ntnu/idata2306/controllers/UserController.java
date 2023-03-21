package no.ntnu.idata2306.controllers;

import no.ntnu.idata2306.data.User;
import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * REST API controller for all endpoints related to Users.
 */
@RestController
public class UserController {
    private List<User> users;

    public UserController(List<User> users) {
        this.users = users;
    }

    /**
     * HTTP GET endpoint for getting all registered users.
     *
     * <b>Should not be available for none admins.</b>
     *
     * @return List of registered users.
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }
}
