package com.speedrundatabaseapi.run;

import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.game.GameRepository;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.platform.PlatformRepository;
import com.speedrundatabaseapi.user.User;
import com.speedrundatabaseapi.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing operations related to runs in the Speedrun Database.
 *
 * <p>This class provides methods for retrieving, adding, updating, and deleting runs. It interacts with
 * the {@link RunRepository}, {@link UserRepository}, {@link GameRepository}, and {@link PlatformRepository}
 * to perform necessary operations on runs, users, games, and platforms.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see RunRepository
 * @see UserRepository
 * @see GameRepository
 * @see PlatformRepository
 */
@Service
public class RunService {

    private final RunRepository runRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;

    /**
     * Constructor for the RunService class.
     *
     * @param runRepository      The repository for accessing and managing runs.
     * @param userRepository     The repository for accessing and managing users.
     * @param gameRepository     The repository for accessing and managing games.
     * @param platformRepository The repository for accessing and managing platforms.
     */
    @Autowired
    public RunService(RunRepository runRepository, UserRepository userRepository,
                      GameRepository gameRepository, PlatformRepository platformRepository) {
        this.runRepository = runRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
    }

    /**
     * Retrieves a list of all runs in the database.
     *
     * @return A list of all runs.
     */
    public List<Run> getAllRuns() {
        return runRepository.findAll();
    }

    /**
     * Adds a new run to the database.
     *
     * @param run The run to be added.
     */
    public void addNewRun(Run run) {
        runRepository.save(run);
    }

    /**
     * Updates the details of a run in the database.
     *
     * @param runId              The ID of the run to be updated.
     * @param updatedRunDetails The updated details for the run.
     */
    public void changeRunDetails(long runId, Run updatedRunDetails) {
        Run run = runRepository.findById(runId)
                .orElseThrow(() -> new EntityNotFoundException("Run with id " + runId + " not found"));

        // Update run details based on non-null properties in updatedRunDetails
        // ...

        runRepository.save(run);
    }

    /**
     * Deletes a run from the database.
     *
     * @param runId The ID of the run to be deleted.
     */
    public void deleteRun(long runId) {
        if (runRepository.existsById(runId)) {
            runRepository.deleteById(runId);
        } else {
            throw new EntityNotFoundException("Run with ID " + runId + " not found");
        }
    }

    /**
     * Retrieves the details of a specific run from the database.
     *
     * @param runId The ID of the run to retrieve details for.
     * @return The run details.
     * @throws EntityNotFoundException If the run with the specified ID is not found.
     */
    public Run getRunDetails(Long runId) {
        return runRepository.findById(runId)
                .orElseThrow(() -> new EntityNotFoundException("Run not found"));
    }
}
