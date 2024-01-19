package com.speedrundatabaseapi.user;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to user operations in the Speedrun Database API.
 * Responsible for CRUD operations on user entities, user registration, and user login.
 *
 * <p>The controller uses the UserService to perform business logic related to user operations.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see UserService
 */
@RestController
@RequestMapping(path = "speedruns/api/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /**
     * Constructor for UserController, injecting the UserService dependency.
     *
     * @param userService The UserService used for user-related business logic.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to retrieve a list of all users.
     *
     * @return List of all users.
     */
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Endpoint to retrieve details of a specific user by ID.
     *
     * @param userId The ID of the user to retrieve details for.
     * @return ResponseEntity containing user details or an error message.
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId) {
        try {
            User user = userService.getUserDetails(userId);
            logger.info("User details fetched successfully");
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while getting user details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting user details");
        }
    }

    /**
     * Endpoint to register a new user.
     *
     * @param user The user object to be registered.
     * @return ResponseEntity indicating success or an error message.
     */
    @PostMapping()
    public ResponseEntity<String> registerNewUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.registerNewUser(user));
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while adding a new user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding a new user");
        }
    }

    /**
     * Endpoint for user login.
     *
     * @param userLoginRequest The request object containing user login credentials.
     * @return ResponseEntity indicating successful login or an error message.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            return ResponseEntity.ok(userService.login(userLoginRequest));
        } catch (BadCredentialsException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while logging in");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while logging in");
        }
    }

    /**
     * Endpoint to delete a user by ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return ResponseEntity indicating success or an error message.
     */
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            logger.info("User with id " + userId + " deleted successfully");
            return ResponseEntity.ok("User with id " + userId + " deleted successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while deleting a user with id " + userId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting a user with id " + userId);
        }
    }

    /**
     * Endpoint to update user details.
     *
     * @param userId             The ID of the user to be updated.
     * @param updatedUserDetails The updated user details.
     * @return ResponseEntity indicating success or an error message.
     */
    @PutMapping(path = "/{userId}")
    public ResponseEntity<String> changeUserDetails(
            @PathVariable Long userId,
            @RequestBody User updatedUserDetails
    ) {
        try {
            userService.changeUserDetails(userId, updatedUserDetails);
            logger.info("User details updated successfully");
            return ResponseEntity.ok("User details updated successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error while updating user details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating user details");
        }
    }
}
