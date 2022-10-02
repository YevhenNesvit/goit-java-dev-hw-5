package services;

import models.User;
import utils.UserUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UserService {
    public static final String USER_WITH_ARRAY = "https://petstore.swagger.io/v2/user/createWithArray/";
    public static final String USER_WITH_LIST = "https://petstore.swagger.io/v2/user/createWithList/";
    public static final String USER = "https://petstore.swagger.io/v2/user/";
    UserUtils userUtils = new UserUtils();

    public void addUsersWithArray(User[] users) throws IOException {
        URL url = new URL(USER_WITH_ARRAY);
        String strings = userUtils.userArrayToString(users);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(strings);

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        } else {
            System.out.println("POST request not worked");
        }
    }

    public void addUsersWithList(List<User> users) throws IOException {
        URL url = new URL(USER_WITH_LIST);
        String strings = userUtils.userListToString(users);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(strings);

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        } else {
            System.out.println("POST request not worked");
        }
    }

    public User getUserByUsername(String username) throws IOException {
        URL url = new URL(USER + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();
//        System.out.println("GET response code: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } //else {
//            System.out.println("GET request not worked");
//        }
        return userUtils.userToObj(response);
    }
}
