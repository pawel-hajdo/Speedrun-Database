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

@Service
public class RunService {

    private final RunRepository runRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    @Autowired
    public RunService(RunRepository runRepository, UserRepository userRepository, GameRepository gameRepository, PlatformRepository platformRepository) {
        this.runRepository = runRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
    }

    public List<Run> getAllRuns() {
        return runRepository.findAll();
    }

    public void addNewRun(Run run) {
        runRepository.save(run);
    }

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

    public void deleteRun(long runId) {
        if(runRepository.existsById(runId)){
            runRepository.deleteById(runId);
        }else{
            throw new EntityNotFoundException("Platform with ID " + runId + " not found");
        }

    }

    public Run getRunDetails(Long runId) {
        return runRepository.findById(runId).orElseThrow(()-> new EntityNotFoundException("Run not found"));
    }
}
