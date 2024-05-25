package services;

import model.User;
import model.dto.LoginUserDto;
import model.dto.UserDto;
import repository.UserRepository;
import utils.SessionManager;

public class UserService {
    private static UserRepository userRepository = new UserRepository();

    public static boolean signUp(UserDto userData){
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            System.out.println("Passwords do not match");
            return false;
        }

        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);

        String role = "user";

        UserDto createUserData = new UserDto(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getUsername(),
                userData.getEmail(),
                salt,
                passwordHash,
                password,
                confirmPassword,
                role
        );

        boolean created = UserRepository.create(createUserData);
        if (created) {
            System.out.println("User created successfully");
        } else {
            System.out.println("Failed to create user");
        }
        return created;
    }

    public static boolean login(LoginUserDto loginData){
        User user = UserRepository.getByUsername(loginData.getUsername());
        if(user == null){
            System.out.println("User not found");
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        boolean passwordMatches = PasswordHasher.compareSaltedHash(password, salt, passwordHash);
        if (passwordMatches) {
            SessionManager.setCurrentUser(user);
            System.out.println("Login successful");
        } else {
            System.out.println("Incorrect password");
        }

        return passwordMatches;
    }

    public static User getUserByUsername(String username) {
        return UserRepository.getByUsername(username);
    }

    public static boolean verifyPassword(User user, String currentPassword) {
        return PasswordHasher.compareSaltedHash(currentPassword, user.getSalt(), user.getPasswordHash());
    }
}
