package no.ntnu.idata2306.security;

import java.util.Random;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * responsible for generating hashed passwords
 * @author Edvin Astad
 * @version 17.03.2023
 */
public class PasswordGenerator {

    /**
     * Takes a password in plane text, adds a salt to the end of it and turns the resulting String in to a hash.
     * @param password password in plane text
     * @param salt random String associated with user
     * @return hash which is used to validate user when logging in
     */
    public static String hashPassword(String password, String salt) {
        // TODO - Refactor method
        String unHashed = password + salt;
        return "" + unHashed.hashCode();
    }

    /**
     * generates random salt
     * @return salt
     */
    public static String generateSalt() {
        // TODO - Refactor method
        Random r = new Random();
        return "" + r.nextInt(1000, 9999);
    }
}
