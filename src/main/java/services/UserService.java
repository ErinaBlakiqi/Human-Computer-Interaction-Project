package services;

import model.User;
import model.dto.UpdateUserDto;
import model.dto.UserDto;
import model.dto.LoginUserDto;
import model.dto.UserDto;
import repository.UserRepository;
import services.PasswordHasher;

import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public static boolean signUp(UserDto userData){
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            return false;
        }

        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        UserDto createUserData = new UserDto(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getUsername(),
                userData.getEmail(),
                salt,
                passwordHash,
                userData.getRole()
        );

        return UserRepository.create(createUserData);
    }

    public static boolean login(LoginUserDto loginData){
        User user = UserRepository.getByUsername(loginData.getUsername());
        if(user == null){
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    ////
    public static User getUserByUsername(String username) {
        return UserRepository.getByUsername(username);
    }

    public static boolean verifyPassword(User user, String currentPassword) {
        return PasswordHasher.compareSaltedHash(currentPassword, user.getSalt(), user.getPasswordHash());
    }
}