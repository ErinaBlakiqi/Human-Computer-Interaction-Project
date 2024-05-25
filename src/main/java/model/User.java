package model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String salt;
    private String passwordHash;
    private String Roli;

    public User(int id, String firstName, String lastName, String username, String email, String salt, String passwordHash, String Roli) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.Roli=Roli;
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

    public String getRoli(){
        return Roli;
    }
    public void setRoli(String Roli){
        this.Roli=Roli;
    }
}
