package com.speedrundatabaseapi.rating;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing GameRating entities in the Speedrun Database API.
 *
 * <p>This interface provides CRUD (Create, Read, Update, Delete) operations for {@link GameRating} entities
 * using Spring Data JPA. It extends the {@link JpaRepository} interface, allowing the use of predefined
 * methods and providing additional custom queries if needed.</p>
 *
 * <p>The generic types specified for {@link JpaRepository} are {@link GameRating}, the entity type, and
 * {@link GameRatingKey}, the type of the composite key for GameRating entities.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see JpaRepository
 * @see GameRating
 * @see GameRatingKey
 */
public interface GameRatingRepository extends JpaRepository<GameRating, GameRatingKey> {
}
