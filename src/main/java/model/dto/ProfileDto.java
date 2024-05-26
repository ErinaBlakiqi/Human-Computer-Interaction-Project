package model.dto;

public class ProfileDto {
    private int userId;
    private String userName;
    private String contactEmail;
    private String location;
    private String contactNumber;
    private String bio;

    // Constructor
    public ProfileDto(int userId, String userName, String contactEmail, String location, String contactNumber, String bio) {
        this.userId = userId;
        this.userName = userName;
        this.contactEmail = contactEmail;
        this.location = location;
        this.contactNumber = contactNumber;
        this.bio = bio;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        if (!contactNumber.matches("\\+?[0-9]*")) {
            throw new IllegalArgumentException("Invalid contact number format");
        }
        this.contactNumber = contactNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
