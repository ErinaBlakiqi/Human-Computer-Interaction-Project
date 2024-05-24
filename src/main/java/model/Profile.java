package model;

public class Profile {
    private int profileId;
    private int userId;
    private String userName;
    private String location;
    private String contactNumber;
    private String contactEmail;
    private String bio;
    private String pictureURL;

    public Profile(int userId, String userName, String location, String contactNumber, String contactEmail, String bio, String pictureURL) {
        this.userId = userId;
        this.userName = userName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.bio = bio;
        this.pictureURL = pictureURL;
    }

    public Profile(int profileId, int userId, String userName, String location, String contactNumber, String contactEmail, String bio, String pictureURL) {
        this.profileId = profileId;
        this.userId = userId;
        this.userName = userName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.bio = bio;
        this.pictureURL = pictureURL;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

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
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
