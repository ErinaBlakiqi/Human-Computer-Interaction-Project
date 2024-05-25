package model.dto;

public class EditProfileDto {
    private int userId;
    private String userName;
    private String location;
    private String contactNumber;
    private String contactEmail;
    private String bio;
    // Constructor
    public EditProfileDto(int userId, String userName, String location, String contactNumber, String contactEmail, String bio) {
        this.userId = userId;
        this.userName = userName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.bio = bio;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocation() {
        return location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getBio() {
        return bio;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        this.userName = userName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContactNumber(String contactNumber) {
        if (!contactNumber.matches("\\+?[0-9]*")) {
            throw new IllegalArgumentException("Invalid contact number format");
        }
        this.contactNumber = contactNumber;
    }

    public void setContactEmail(String contactEmail) {
        if (!contactEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.contactEmail = contactEmail;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
