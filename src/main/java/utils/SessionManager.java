package utils;

import model.User;

public class SessionManager {
    private static User currentUser;
    private static int currentUserId;

    public static void setCurrentUser(User user) {
        currentUser = user;
        currentUserId = user.getId();
    }
    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static User getLoggedInUser() {
        return currentUser;
    }


    public static boolean isAdmin() {
        return currentUser != null && "admin".equalsIgnoreCase(currentUser.getRoli());
    }
}
