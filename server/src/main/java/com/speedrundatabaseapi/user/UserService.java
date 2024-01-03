package com.speedrundatabaseapi.user;

import com.speedrundatabaseapi.config.JwtService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String registerNewUser(User newUser){
        Optional<User> userWithSameEmail = userRepository.findByEmail(newUser.getEmail());
        Optional<User> userWithSameLogin = userRepository.findByLogin(newUser.getLogin());

        if(userWithSameEmail.isPresent()){
            throw new RuntimeException("User with this email already exists");
        }
        if(userWithSameLogin.isPresent()){
            throw new RuntimeException("User with this login already exists");
        }

        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

        String jwtToken = jwtService.generateToken(newUser);
        return jwtToken;
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
            String hashedPassword = passwordEncoder.encode(updatedUserDetails.getPassword());
            user.setPassword(hashedPassword);
        }
        if(updatedUserDetails.getRole() != null){
            user.setRole(updatedUserDetails.getRole());
        }

        userRepository.save(user);
    }

    public String login(UserLoginRequest userLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getLogin(),
                        userLoginRequest.getPassword()
                )
        );
        User user = userRepository.findByLogin(userLoginRequest.getLogin()).orElseThrow(()->new EntityNotFoundException("User with login: " +userLoginRequest.getLogin()+ " not found"));
        String jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }

    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

}
