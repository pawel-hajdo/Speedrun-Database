package com.speedrundatabaseapi.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/run")
public class RunController {

    private final Logger logger = LoggerFactory.getLogger(RunController.class);
    private final RunService runService;
    @Autowired
    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping
    public List<Run> GetAllRuns(){
        return runService.getAllRuns();
    }

    @PostMapping
    public ResponseEntity<String> addNewRun(@RequestBody Run run){
        try{
            runService.addNewRun(run);
            logger.info("Run added successfully");
            return ResponseEntity.ok("Run added successfully");
        } catch (Exception e) {
            logger.error("Error occurred while adding a new run");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding a new run");
        }
    }
}
