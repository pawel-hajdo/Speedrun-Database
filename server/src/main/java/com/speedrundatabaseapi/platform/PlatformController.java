package com.speedrundatabaseapi.platform;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "speedruns/api/platforms")
public class PlatformController {

    private final Logger logger = LoggerFactory.getLogger(PlatformController.class);
    private final PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public List<Platform> getAllPlatforms(){
        return platformService.getAllPlatforms();
    }

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
