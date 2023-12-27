package com.speedrundatabaseapi.platform;

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
}
