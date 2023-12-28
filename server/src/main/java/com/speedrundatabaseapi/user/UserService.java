package com.speedrundatabaseapi.user;

import jakarta.persistence.EntityNotFoundException;
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

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void changeUserDetails(Long userId, User updatedUserDetails) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User with id " +userId + " not found"));
        user.setEmail(updatedUserDetails.getEmail());
        user.setLogin(updatedUserDetails.getLogin());
        user.setPassword(updatedUserDetails.getPassword());
        user.setRole(updatedUserDetails.getRole());

        userRepository.save(user);
    }
}
