package services;

import model.dto.*;
import repository.ProfileRepository;

import java.sql.SQLException;
import java.util.List;

public class ProfileService {
    private ProfileRepository profileRepository = new ProfileRepository();

    public ProfileDto fetchProfileData(int userId) {
        return profileRepository.fetchProfileData(userId);
    }

    public boolean updateProfile(EditProfileDto editProfileDto) throws SQLException {
        return profileRepository.updateProfile(editProfileDto);
    }

    public List<DailyChartDto> fetchBoughtProductsData(int userId) {
        return profileRepository.fetchBoughtProductsData(userId);
    }

    public List<ProfileOrderDto> fetchLastBoughtItems(int userId) {
        return profileRepository.fetchLastBoughtItems(userId);
    }
}
