package com.speedrundatabaseapi.user;

/**
 * Represents a user login request containing login credentials (username and password).
 * Used for user authentication during the login process.
 *
 * <p>This class is designed to be used as a request body in the login endpoint of the UserController.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see UserController
 */
public class UserLoginRequest {
    private String login;
    private String password;

    /**
     * Default constructor for UserLoginRequest.
     */
    public UserLoginRequest() {
    }

    /**
     * Parameterized constructor for UserLoginRequest.
     *
     * @param login    The username for login.
     * @param password The password for login.
     */
    public UserLoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Getter for the login username.
     *
     * @return The login username.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for the login username.
     *
     * @param login The new login username.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for the login password.
     *
     * @return The login password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the login password.
     *
     * @param password The new login password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
