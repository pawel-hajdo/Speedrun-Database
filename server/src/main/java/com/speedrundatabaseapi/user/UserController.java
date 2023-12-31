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

@RestController
@RequestMapping(path = "speedruns/api/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private String emailSubject = "Hello";
    private String emailText = "Welcome in Game Speedruns Database!";
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public ResponseEntity<String> registerNewUser(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.registerNewUser(user));
        }catch (RuntimeException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while adding a new user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding a new user");
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        try {
            return ResponseEntity.ok(userService.login(userLoginRequest));
        }catch(BadCredentialsException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while logging in");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while logging in");
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        try{
            userService.deleteUser(userId);
            logger.info("User with id " + userId + " deleted successfully");
            return ResponseEntity.ok("User with id " + userId + " deleted successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while deleting a user with id " +userId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting a user with id " +userId);
        }
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<String> changeUserDetails(
            @PathVariable Long userId,
            @RequestBody User updatedUserDetails
    ){
        try{
            userService.changeUserDetails(userId, updatedUserDetails);
            logger.info("User details updated successfully");
            return ResponseEntity.ok("User details updated successfully");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error while updating user details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating user details");
        }
    }
}
