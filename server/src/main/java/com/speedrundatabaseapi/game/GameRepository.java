package com.speedrundatabaseapi.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Game entities.
 *
 * <p>This interface extends {@link JpaRepository}, providing methods for common
 * database operations, such as saving, updating, and deleting games.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
