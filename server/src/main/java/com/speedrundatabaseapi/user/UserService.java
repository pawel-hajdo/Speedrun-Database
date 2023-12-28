package com.speedrundatabaseapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void registerNewUser(User newUser){
        Optional<User> userWithSameEmail = userRepository.findByEmail(newUser.getEmail());
        Optional<User> userWithSameLogin = userRepository.findByLogin(newUser.getLogin());

        if(userWithSameEmail.isPresent()){
            throw new RuntimeException("User with this email already exists");
        }
        if(userWithSameLogin.isPresent()){
            throw new RuntimeException("User with this login already exists");
        }

        userRepository.save(newUser);
    }
}
