package com.speedrundatabaseapi.user;

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
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while adding a new user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding a new user");
        }
    }
}
