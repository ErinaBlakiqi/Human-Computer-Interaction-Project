package services;

import model.dto.EditProfileDto;
import repository.ProfileRepository;

public class ProfileService {
    private ProfileRepository profileRepository = new ProfileRepository();

    public boolean updateProfile(EditProfileDto editProfileDto) {
        return profileRepository.updateProfile(editProfileDto);
    }
}
