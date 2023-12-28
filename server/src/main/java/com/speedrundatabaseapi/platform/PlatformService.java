package com.speedrundatabaseapi.platform;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlatformService {

    private final PlatformRepository platformRepository;
    @Autowired
    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<Platform> getAllPlatforms(){
        return platformRepository.findAll();
    }

    public void addNewPlatform(Platform platform){
        platformRepository.save(platform);
    }

    public void deletePlatform(Long platformId) {
        if(platformRepository.existsById(platformId)){
            platformRepository.deleteById(platformId);
        }else{
            throw new EntityNotFoundException("Platform with ID " + platformId + " not found");
        }
    }

    public void changePlatformDetails(Long platformId, Platform updatedPlatformDetails) {
        Platform platform = platformRepository.findById(platformId).orElseThrow(()-> new EntityNotFoundException("Platform with ID " + platformId + " not found"));

        if(updatedPlatformDetails.getName() != null){
            platform.setName(updatedPlatformDetails.getName());
        }
        if(updatedPlatformDetails.getType() != null){
            platform.setType(updatedPlatformDetails.getType());
        }

        platformRepository.save(platform);
    }
}
