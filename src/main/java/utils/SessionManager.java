package utils;

import model.User;

public class SessionManager {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getLoggedInUser() {
        return currentUser;
    }

    public static boolean isAdmin() {
        return currentUser != null && "admin".equalsIgnoreCase(currentUser.getRoli());
    }
}