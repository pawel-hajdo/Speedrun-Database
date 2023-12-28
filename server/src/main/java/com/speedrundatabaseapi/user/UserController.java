package com.speedrundatabaseapi.user;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerNewUser(@RequestBody User user){
        try{
            userService.registerNewUser(user);
            logger.info("User added successfully");
            return ResponseEntity.ok("User added successfully");
        }catch (RuntimeException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while adding a new user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding a new user");
        }
    }

    @DeleteMapping(path = "/user/{userId}")
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

    @PutMapping(path = "/user/{userId}")
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