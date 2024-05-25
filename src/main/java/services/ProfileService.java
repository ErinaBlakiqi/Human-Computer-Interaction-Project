package services;

import model.dto.EditProfileDto;
import model.dto.ProfileDto;
import repository.ProfileRepository;

import java.sql.SQLException;

public class ProfileService {
    private ProfileRepository profileRepository = new ProfileRepository();

    public ProfileDto fetchProfileData(int userId) {
        return profileRepository.fetchProfileData(userId);
    }
    public boolean updateProfile(EditProfileDto editProfileDto) throws SQLException {
        return profileRepository.updateProfile(editProfileDto);
    }
}
