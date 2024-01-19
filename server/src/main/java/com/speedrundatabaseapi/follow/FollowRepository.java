package com.speedrundatabaseapi.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Follow entities.
 *
 * <p>The FollowRepository interface provides methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Follow entities. It extends the JpaRepository interface from Spring Data JPA.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    /**
     * Delete a follow relationship by its composite key.
     *
     * @param followKey The composite key representing the follow relationship.
     */
    void deleteById(FollowKey followKey);
}
