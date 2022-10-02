package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;
import services.UserService;

import java.io.IOException;
import java.util.List;

public class UserUtils {

    public String userArrayToString(User[] users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(users);
    }

    public String userListToString(List<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(users);
    }

    public User userToObj(StringBuffer response) {
        String userToObj = String.valueOf(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(userToObj, User.class);
    }

    public String userToString(Integer id, String username, String firstName, String lastName, String email, String password,
                               String phone, Integer userStatus) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(user);
    }

    public boolean IsUserExists(String username) throws IOException {
        UserService userService = new UserService();
        return userService.getUserByUsername(username) != null;
    }
}
