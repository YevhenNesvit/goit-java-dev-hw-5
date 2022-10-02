package command;

import services.UserService;
import utils.UserUtils;
import view.View;

import java.io.IOException;

public class PutUserByUsername implements Command {
    public static final String PUT_USER_BY_USERNAME = "update user by username";
    private final View view;
    UserUtils userUtils = new UserUtils();
    UserService userService = new UserService();

    public PutUserByUsername(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(PUT_USER_BY_USERNAME);
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

        try {
            while (true) {
                try {
                    view.write("Please, enter new id of user to update: ");
                    id = Integer.parseInt(view.read());
                    view.write("Please, enter new firstName of user to update: ");
                    firstName = view.read();
                    view.write("Please, enter new lastName of user to update: ");
                    lastName = view.read();
                    view.write("Please, enter new email of user to update: ");
                    email = view.read();
                    view.write("Please, enter new password of user to update: ");
                    password = view.read();
                    view.write("Please, enter new phone of user to update: ");
                    phone = view.read();
                    view.write("Please, enter new userStatus of user to update: ");
                    userStatus = Integer.parseInt(view.read());
                    view.write("Please, enter username of user to update: ");
                    username = view.read();
                    if (userUtils.IsUserExists(username)) {
                        userService.updateUserByUsername(id, username, firstName, lastName, email, password, phone, userStatus);
                        view.write("User with username " + username + " successfully updated");
                        break;
                    } else {
                        System.out.println("User with this Username doesn't exists");
                    }
                } catch (NumberFormatException e) {
                    view.write("Invalid value");
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
