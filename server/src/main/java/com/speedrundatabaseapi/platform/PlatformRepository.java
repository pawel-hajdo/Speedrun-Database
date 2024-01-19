package com.speedrundatabaseapi.platform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for interacting with the database for platform-related operations.
 *
 * <p>This interface extends the JpaRepository interface, providing CRUD (Create, Read, Update, Delete) operations
 * for the Platform entity.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
