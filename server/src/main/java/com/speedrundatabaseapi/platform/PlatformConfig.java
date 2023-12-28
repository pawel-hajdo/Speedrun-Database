package com.speedrundatabaseapi.platform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PlatformConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlatformRepository platformRepository){
        return args -> {
            List<Platform> platformsList = new ArrayList<>();
            platformsList.add(new Platform(PlatformType.PC, "PC"));
            platformsList.add(new Platform(PlatformType.CONSOLE, "Playstation 5"));
            platformsList.add(new Platform(PlatformType.MOBILE, "Phone"));

            platformRepository.saveAll(platformsList);
        };
    }
}
