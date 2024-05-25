package utils;

import model.User;

public class SessionManager {
    private static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void clearSession() {
        loggedInUser = null;
    }

    public static boolean isAdmin() {
        return loggedInUser != null && "admin".equalsIgnoreCase(loggedInUser.getRole());
    }
}