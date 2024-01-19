package com.speedrundatabaseapi.run;

import com.speedrundatabaseapi.platform.Platform;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to runs in the Speedrun Database API.
 *
 * <p>This class defines endpoints for retrieving all runs, getting details of a specific run,
 * adding a new run, updating run details, and deleting a run.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see RunService
 */
@RestController
@RequestMapping(path = "speedruns/api/runs")
public class RunController {

    private final Logger logger = LoggerFactory.getLogger(RunController.class);
    private final RunService runService;

    /**
     * Constructor for RunController, injecting dependencies.
     *
     * @param runService The RunService for handling business logic related to runs.
     */
    @Autowired
    public RunController(RunService runService) {
        this.runService = runService;
    }

    /**
     * Endpoint for retrieving a list of all runs.
     *
     * @return List of all runs.
     */
    @GetMapping
    public List<Run> getAllRuns() {
        return runService.getAllRuns();
    }

    /**
     * Endpoint for retrieving details of a specific run by its ID.
     *
     * @param runId The ID of the run.
     * @return ResponseEntity containing the run details or an error message.
     */
    @GetMapping(path = "/{runId}")
    public ResponseEntity<?> getRunDetails(@PathVariable Long runId) {
        try {
            Run run = runService.getRunDetails(runId);
            logger.info("Run details fetched successfully");
            return ResponseEntity.ok(run);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while getting run details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting run details");
        }
    }

    /**
     * Endpoint for adding a new run.
     *
     * @param run The run to be added.
     * @return ResponseEntity with success message or an error message.
     */
    @PostMapping
    public ResponseEntity<String> addNewRun(@RequestBody Run run) {
        try {
            runService.addNewRun(run);
            logger.info("Run added successfully");
            return ResponseEntity.ok("Run added successfully");
        } catch (Exception e) {
            logger.error("Error occurred while adding a new run");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding a new run");
        }
    }

    /**
     * Endpoint for updating details of a specific run.
     *
     * @param runId             The ID of the run to be updated.
     * @param updatedRunDetails The updated run details.
     * @return ResponseEntity with success message or an error message.
     */
    @PutMapping(path = "/{runId}")
    public ResponseEntity<String> changeRunDetails(
            @PathVariable long runId,
            @RequestBody Run updatedRunDetails
    ) {
        try {
            runService.changeRunDetails(runId, updatedRunDetails);
            logger.info("Run details updated successfully");
            return ResponseEntity.ok("Run details updated successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error while updating run details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating run details");
        }
    }

    /**
     * Endpoint for deleting a specific run by its ID.
     *
     * @param runId The ID of the run to be deleted.
     * @return ResponseEntity with success message or an error message.
     */
    @DeleteMapping(path = "/{runId}")
    public ResponseEntity<String> deleteRun(@PathVariable long runId) {
        try {
            runService.deleteRun(runId);
            logger.info("Run deleted successfully");
            return ResponseEntity.ok("Run deleted successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while deleting a run");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting a run");
        }
    }
}
