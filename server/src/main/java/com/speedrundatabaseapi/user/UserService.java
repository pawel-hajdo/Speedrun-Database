package com.speedrundatabaseapi.user;

import com.speedrundatabaseapi.config.JwtService;
import com.speedrundatabaseapi.email.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to user operations in the Speedrun Database API.
 *
 * <p>This class is responsible for user registration, login, fetching user details, updating user details, and deleting users.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see User
 * @see UserLoginRequest
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final String registrationEmailSubject = "Welcome to the Speedruns Database!";
    private final String registrationEmailText = "Dear Speedrun Enthusiast,\n" +
            "\n" +
            "Welcome to the Speedruns Database, your gateway to the thrilling world of game speedrunning! We're delighted that you've joined our community dedicated to the pursuit of speed and skill in gaming.\n" +
            "Get ready for an exciting journey through the realm of Speedruns!\n" +
            "\n" +
            "Best regards,\n" +
            "\n" +
            "The Speedruns Database Team";

    /**
     * Constructor for UserService, injecting dependencies.
     *
     * @param userRepository         The UserRepository for database operations related to User entities.
     * @param passwordEncoder        The PasswordEncoder for encoding and decoding passwords.
     * @param jwtService             The JwtService for generating and validating JWT tokens.
     * @param emailService           The EmailService for sending emails.
     * @param authenticationManager  The AuthenticationManager for handling user authentication.
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, EmailService emailService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Retrieves a list of all users in the database.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Registers a new user in the database.
     *
     * @param newUser The user to be registered.
     * @return JWT token for the registered user.
     */
    public String registerNewUser(User newUser) {
        Optional<User> userWithSameEmail = userRepository.findByEmail(newUser.getEmail());
        Optional<User> userWithSameLogin = userRepository.findByLogin(newUser.getLogin());

        if (userWithSameEmail.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        if (userWithSameLogin.isPresent()) {
            throw new RuntimeException("User with this login already exists");
        }

        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);
        emailService.send(newUser.getEmail(), registrationEmailSubject, registrationEmailText);
        return jwtService.generateToken(newUser);
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId The ID of the user to be deleted.
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Updates user details in the database.
     *
     * @param userId             The ID of the user to be updated.
     * @param updatedUserDetails The updated user details.
     */
    public void changeUserDetails(Long userId, User updatedUserDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));

        if (updatedUserDetails.getEmail() != null) {
            user.setEmail(updatedUserDetails.getEmail());
        }
        if (updatedUserDetails.getLogin() != null) {
            user.setLogin(updatedUserDetails.getLogin());
        }
        if (updatedUserDetails.getPassword() != null) {
            String hashedPassword = passwordEncoder.encode(updatedUserDetails.getPassword());
            user.setPassword(hashedPassword);
        }
        if (updatedUserDetails.getRole() != null) {
            user.setRole(updatedUserDetails.getRole());
        }

        userRepository.save(user);
    }

    /**
     * Authenticates a user based on login credentials and returns a JWT token.
     *
     * @param userLoginRequest The login credentials.
     * @return JWT token for the authenticated user.
     */
    public String login(UserLoginRequest userLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getLogin(),
                        userLoginRequest.getPassword()
                )
        );
        User user = userRepository.findByLogin(userLoginRequest.getLogin()).orElseThrow(() -> new EntityNotFoundException("User with login: " + userLoginRequest.getLogin() + " not found"));
        return jwtService.generateToken(user);
    }

    /**
     * Retrieves details of a user by their ID.
     *
     * @param userId The ID of the user.
     * @return User details.
     */
    public User getUserDetails(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
