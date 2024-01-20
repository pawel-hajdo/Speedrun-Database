package com.speedrundatabaseapi.run;

import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.game.GameRepository;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.platform.PlatformRepository;
import com.speedrundatabaseapi.user.User;
import com.speedrundatabaseapi.user.UserRepository;
import com.speedrundatabaseapi.user.UserRole;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.speedrundatabaseapi.user.UserRole.ADMIN;

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
        Run run = runRepository.findById(runId).orElseThrow(()-> new EntityNotFoundException("Run with id " +runId+ " not found"));


        if(updatedRunDetails.getTime() != null){
            run.setTime(updatedRunDetails.getTime());
        }
        if(updatedRunDetails.getVideoLink() != null){
            run.setVideoLink(updatedRunDetails.getVideoLink());
        }
        if(updatedRunDetails.getType() != null){
            run.setType(updatedRunDetails.getType());
        }
        if (updatedRunDetails.getUser() != null) {
            User user = userRepository.findById(updatedRunDetails.getUser().getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + updatedRunDetails.getUser().getUserId() + " not found"));

            run.setUser(user);
        }
        if (updatedRunDetails.getGame() != null) {
            Game game = gameRepository.findById(updatedRunDetails.getGame().getGameId())
                    .orElseThrow(() -> new EntityNotFoundException("Game with id " + updatedRunDetails.getGame().getGameId() + " not found"));

            run.setGame(game);
        }
        if (updatedRunDetails.getPlatform() != null) {
            Platform platform = platformRepository.findById(updatedRunDetails.getPlatform().getPlatformId())
                    .orElseThrow(() -> new EntityNotFoundException("Platform with id " + updatedRunDetails.getPlatform().getPlatformId() + " not found"));

            run.setPlatform(platform);
        }

        runRepository.save(run);
    }
    /**
     * Confirms a run with provided id.
     *
     * @param runId The ID of the run to be deleted.
     * @param userId The ID of user confirming run.
     */
    public void confirmRun(Long runId, Long userId){
        Run run = runRepository.findById(runId).orElseThrow(()-> new EntityNotFoundException("Run with id " +runId+ " not found"));
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User with id " +userId+ " not found"));

        UserRole userRole = user.getRole();
        if(userRole == ADMIN){
            run.setConfirmedBy(userId);
            runRepository.save(run);
        }else {
            throw new AccessDeniedException("You are not allowed to perform this action");
        }
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
