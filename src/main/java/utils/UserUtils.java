package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;

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
}
