package model.dto;

public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String salt;
    private String passwordHash;
    private String password;
    private String confirmPassword;

    public UserDto(int id, String firstName, String lastName, String username, String email, String salt, String passwordHash, String password, String confirmPassword) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserDto(String firstName, String lastName, String username, String email, String salt, String passwordHash, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserDto(String firstName, String lastName, String username, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
