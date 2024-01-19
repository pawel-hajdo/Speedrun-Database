package com.speedrundatabaseapi.run;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing {@link Run} entities in the Speedrun Database.
 *
 * <p>This repository extends {@link JpaRepository}, providing CRUD operations for the {@link Run} entity.
 * It is annotated with {@code @Repository} to indicate that it is a Spring Data repository bean.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see JpaRepository
 * @see Run
 */
@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
}
