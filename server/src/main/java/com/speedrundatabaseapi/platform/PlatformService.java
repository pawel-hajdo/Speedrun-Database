package com.speedrundatabaseapi.platform;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class providing business logic for platform-related operations.
 *
 * <p>This class contains methods to interact with the PlatformRepository and perform operations such as
 * retrieving all platforms, adding a new platform, deleting a platform, updating platform details,
 * and getting platform details.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Service
public class PlatformService {

    private final PlatformRepository platformRepository;

    /**
     * Constructs a PlatformService with the specified PlatformRepository.
     *
     * @param platformRepository The repository for platform-related operations.
     */
    @Autowired
    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    /**
     * Retrieves a list of all platforms.
     *
     * @return List of Platform entities.
     */
    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    /**
     * Adds a new platform.
     *
     * @param platform The platform entity to be added.
     */
    public void addNewPlatform(Platform platform) {
        platformRepository.save(platform);
    }

    /**
     * Deletes a platform by ID.
     *
     * @param platformId The ID of the platform to be deleted.
     * @throws EntityNotFoundException If the platform with the given ID is not found.
     */
    public void deletePlatform(Long platformId) {
        if (platformRepository.existsById(platformId)) {
            platformRepository.deleteById(platformId);
        } else {
            throw new EntityNotFoundException("Platform with ID " + platformId + " not found");
        }
    }

    /**
     * Updates platform details.
     *
     * @param platformId            The ID of the platform to be updated.
     * @param updatedPlatformDetails The updated details for the platform.
     * @throws EntityNotFoundException If the platform with the given ID is not found.
     */
    public void changePlatformDetails(Long platformId, Platform updatedPlatformDetails) {
        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(() -> new EntityNotFoundException("Platform with ID " + platformId + " not found"));

        if (updatedPlatformDetails.getName() != null) {
            platform.setName(updatedPlatformDetails.getName());
        }
        if (updatedPlatformDetails.getType() != null) {
            platform.setType(updatedPlatformDetails.getType());
        }

        platformRepository.save(platform);
    }

    /**
     * Retrieves details of a specific platform by ID.
     *
     * @param platformId The ID of the platform.
     * @return The Platform entity with the given ID.
     * @throws EntityNotFoundException If the platform with the given ID is not found.
     */
    public Platform getPlatformDetails(Long platformId) {
        return platformRepository.findById(platformId)
                .orElseThrow(() -> new EntityNotFoundException("Platform not found"));
    }
}
