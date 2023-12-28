package com.speedrundatabaseapi.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/platform")
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

}
