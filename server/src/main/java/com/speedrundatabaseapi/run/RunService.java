package com.speedrundatabaseapi.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {

    private final RunRepository runRepository;
    @Autowired
    public RunService(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    public List<Run> getAllRuns() {
        return runRepository.findAll();
    }

    public void addNewRun(Run run) {
        runRepository.save(run);
    }
}
