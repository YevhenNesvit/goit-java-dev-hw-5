package command;

import services.UserService;
import utils.UserUtils;
import view.View;

import java.io.IOException;

public class DeleteUserByUsername implements Command {
    public static final String DELETE_USER_BY_USERNAME = "delete user by username";
    private final View view;
    UserUtils userUtils = new UserUtils();
    UserService userService = new UserService();

    public DeleteUserByUsername(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_USER_BY_USERNAME);
    }

    @Override
    public void execute() {
        String username;
        try {
            while (true) {
                view.write("Please, enter username of user to delete: ");
                username = view.read();
                if (userUtils.IsUserExists(username)) {
                    userService.deleteUserByUsername(username);
                    view.write("User with username " + username + " successfully deleted");
                    break;
                } else {
                    System.out.println("Username doesn't exists");
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
