package services;

import model.dto.UpdateUserDto;
import repository.ProfileRepository;

public class ChangePasswordService {

    public static boolean updatePassword(UpdateUserDto updateUserDto) {
        String newPassword = updateUserDto.getSaltedPassword();
        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(newPassword, salt);

        return ProfileRepository.updatePassword(updateUserDto.getId(), passwordHash, salt);
    }
}
