package application;
import model.User;

public final class UserManager {
    private static User user;

    public static void setUser(User user){
        UserManager.user = user;
    }

    public static User getUser(){
        return UserManager.user;
    }
}

