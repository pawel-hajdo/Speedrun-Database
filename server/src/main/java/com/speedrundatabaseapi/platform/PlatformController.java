package com.speedrundatabaseapi.platform;

import com.speedrundatabaseapi.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling platform-related HTTP requests in the Speedrun Database API.
 *
 * <p>This class defines RESTful endpoints to perform CRUD operations on gaming platforms, including retrieving all platforms,
 * getting details of a specific platform, adding a new platform, updating platform details, and deleting a platform.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@RestController
@RequestMapping(path = "speedruns/api/platforms")
public class PlatformController {

    private final Logger logger = LoggerFactory.getLogger(PlatformController.class);
    private final PlatformService platformService;

    /**
     * Constructor for PlatformController.
     *
     * @param platformService The service responsible for handling platform-related operations.
     */
    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    /**
     * Retrieves a list of all gaming platforms.
     *
     * @return List of all gaming platforms.
     */
    @GetMapping
    public List<Platform> getAllPlatforms(){
        return platformService.getAllPlatforms();
    }

    /**
     * Gets the details of a specific gaming platform.
     *
     * @param platformId The ID of the platform to retrieve details for.
     * @return ResponseEntity containing platform details or an error message if not found.
     */
    @GetMapping(path = "/{platformId}")
    public ResponseEntity<?> getPlatformDetails(@PathVariable Long platformId){
        try{
            Platform platform = platformService.getPlatformDetails(platformId);
            logger.info("Platform details fetched successfully");
            return ResponseEntity.ok(platform);
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while getting platform details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting platform details");
        }
    }

    /**
     * Adds a new gaming platform.
     *
     * @param platform The platform to be added.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping
    public ResponseEntity<String> addNewPlatform(@RequestBody Platform platform){
        try{
            platformService.addNewPlatform(platform);
            logger.info("Platform added successfully");
            return ResponseEntity.ok("Platform added successfully");
        }catch (Exception e){
            logger.error("Error occurred while adding a new platform");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding a new platform");
        }
    }

    /**
     * Deletes a gaming platform.
     *
     * @param platformId The ID of the platform to be deleted.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @DeleteMapping(path = "/{platformId}")
    public ResponseEntity<String> deletePlatform(@PathVariable Long platformId){
        try{
            platformService.deletePlatform(platformId);
            logger.info("Platform deleted successfully");
            return ResponseEntity.ok("Platform deleted successfully");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while deleting a platform");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting a platform");
        }
    }

    /**
     * Updates the details of a gaming platform.
     *
     * @param platformId The ID of the platform to be updated.
     * @param updatedPlatformDetails The updated details of the platform.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PutMapping(path = "/{platformId}")
    public ResponseEntity<String> changePlatformDetails(
            @PathVariable Long platformId,
            @RequestBody Platform updatedPlatformDetails
    ){
        try{
            platformService.changePlatformDetails(platformId, updatedPlatformDetails);
            logger.info("Platform details updated successfully");
            return ResponseEntity.ok("Platform details updated successfully");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error while updating platform details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating platform details");
        }
    }
}
