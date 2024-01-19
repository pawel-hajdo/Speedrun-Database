package com.speedrundatabaseapi.config;

import com.speedrundatabaseapi.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for the application.
 *
 * <p>This class provides configuration for user authentication, including the setup of a
 * custom {@link UserDetailsService}, an {@link AuthenticationProvider}, and a {@link PasswordEncoder}.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Constructs an ApplicationConfig with the specified UserRepository.
     *
     * @param userRepository The repository for User entities.
     */
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a custom UserDetailsService bean.
     *
     * @return The UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Creates an AuthenticationProvider bean using a DaoAuthenticationProvider.
     *
     * @return The AuthenticationProvider implementation.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Creates an AuthenticationManager bean using the provided AuthenticationConfiguration.
     *
     * @param configuration The AuthenticationConfiguration.
     * @return The AuthenticationManager implementation.
     * @throws Exception If an exception occurs during the creation of the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Creates a PasswordEncoder bean using BCryptPasswordEncoder.
     *
     * @return The PasswordEncoder implementation.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
