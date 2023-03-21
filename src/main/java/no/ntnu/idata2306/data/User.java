package no.ntnu.idata2306.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import no.ntnu.idata2306.SecurityController;


@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "first name", nullable = false)
    private String firstName;
    @Column(name = "last name", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "salt", nullable = false)
    private String salt;

    /**
     * Constructor with parameters.
     *
     * @param id id which will represent the user in the database.
     * @param email users email.
     * @param firstName first and middle name(s) name of user.
     * @param lastName surname of user.
     * @param password users password.
     */
    public User(int id, String email, String firstName, String lastName, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = SecurityController.generateSalt();
        this.password = password;
    }

    /** Empty constructor */
    public User() {
    }



    /** sets the value of fistName field to given value */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** sets value of lastName field to given value */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** returns email */
    public String getEmail() {
        return email;
    }

    /** returns first name */
    public String getFirstName() {
        return firstName;
    }

    /** returns surname */
    public String getLastName() {
        return lastName;
    }

    /** returns hashed password */
    public String getPassword() {
        return password;
    }

    /** returns salt */
    public String getSalt() {
        return salt;
    }

    /**
     * sets password to new password provided by user.
     *
     * @param password hash which will correspond with user password + salt.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
