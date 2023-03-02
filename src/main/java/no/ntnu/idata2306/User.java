package no.ntnu.idata2306;

public class User {

    // TODO - implement database and create id field which represents user id.
    private final String email;
    private String firstName;
    private String lastName;
    private String password;
    private final String salt;

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = SecurityController.generateSalt();
        this.password = null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    /**
     * sets password to new password provided by user.
     *
     * <b>There will be no checks in this method as the server will be receiving the hashed password only.
     * All checks to validate password must thus be done by website.</b>
     *
     * @param password hash which will correspond with user password + salt.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
