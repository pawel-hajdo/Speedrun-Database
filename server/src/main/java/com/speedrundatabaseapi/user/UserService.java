package com.speedrundatabaseapi.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
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

        String hashedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void changeUserDetails(Long userId, User updatedUserDetails) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User with id " +userId + " not found"));

        if(updatedUserDetails.getEmail() != null){
            user.setEmail(updatedUserDetails.getEmail());
        }
        if(updatedUserDetails.getLogin() != null){
            user.setLogin(updatedUserDetails.getLogin());
        }
        if(updatedUserDetails.getPassword() != null){
            String hashedPassword = bCryptPasswordEncoder.encode(updatedUserDetails.getPassword());
            user.setPassword(hashedPassword);
        }
        if(updatedUserDetails.getRole() != null){
            user.setRole(updatedUserDetails.getRole());
        }

        userRepository.save(user);
    }

    public void login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByLogin(userLoginRequest.getLogin()).orElseThrow(()->new EntityNotFoundException("User with login: " +userLoginRequest.getLogin()+ " not found"));

        String userHashedPassword = user.getPassword();

        if(!bCryptPasswordEncoder.matches(userLoginRequest.getPassword(), userHashedPassword)){
            throw new InvalidPasswordException("Wrong password");
        }
    }

    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

}
