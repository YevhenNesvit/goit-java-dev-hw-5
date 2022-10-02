package command;

import services.UserService;
import view.View;

import java.io.IOException;

public class GetUserByUsername implements Command {
    public static final String GET_USER_BY_USERNAME = "user by username";
    private final View view;
    UserService userService = new UserService();

    public GetUserByUsername(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_USER_BY_USERNAME);
    }

    @Override
    public void execute() {
        String username;
        while (true) {
                view.write("Please, enter username: ");
                username = view.read();
                break;
        }
        try {
            view.write("For username " + username + " user is:\n" + userService.getUserByUsername(username));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
