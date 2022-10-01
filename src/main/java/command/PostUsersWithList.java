package command;

import models.User;
import services.UserService;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostUsersWithList implements Command {
    public static final String POST_USER_WITH_LIST = "add users with list";
    private final View view;
    UserService userService = new UserService();

    public PostUsersWithList(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(POST_USER_WITH_LIST);
    }

    @Override
    public void execute() {
        int id;
        String username;
        String firstName;
        String lastName;
        String email;
        String password;
        String phone;
        int userStatus;
        List<User> users = new ArrayList<>();
        try {
            while (true) {
                boolean userRun = true;
                try {
                    do {
                        view.write("Please, enter id of new user: ");
                        id = Integer.parseInt(view.read());
                        view.write("Please, enter username of new user: ");
                        username = view.read();
                        view.write("Please, enter firstName of new user: ");
                        firstName = view.read();
                        view.write("Please, enter lastName of new user: ");
                        lastName = view.read();
                        view.write("Please, enter email of new user: ");
                        email = view.read();
                        view.write("Please, enter password of new user: ");
                        password = view.read();
                        view.write("Please, enter phone of new user: ");
                        phone = view.read();
                        view.write("Please, enter userStatus of new user: ");
                        userStatus = Integer.parseInt(view.read());
                        users.add(new User(id, username, firstName, lastName, email, password, phone, userStatus));
                        System.out.print("Another user (y/n)?: ");
                        if (view.read().equalsIgnoreCase("n")) {
                            userRun = false;
                        }
                    } while (userRun);
                    break;
                } catch (NumberFormatException e) {
                    view.write("Invalid value");
                }
            }
            userService.addUsersWithList(users);
            view.write("Users in list successfully added");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
