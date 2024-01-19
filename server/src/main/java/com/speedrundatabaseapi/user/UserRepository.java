package com.speedrundatabaseapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entities, extending JpaRepository for basic CRUD operations.
 *
 * <p>This interface provides additional query methods for retrieving User entities by login and email.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their login username.
     *
     * @param login The login username of the user.
     * @return An Optional containing the user if found, otherwise an empty Optional.
     */
    Optional<User> findByLogin(String login);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return An Optional containing the user if found, otherwise an empty Optional.
     */
    Optional<User> findByEmail(String email);
}
