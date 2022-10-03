package command;

import services.UserService;
import view.View;

import java.io.IOException;

public class GetUserLogout implements Command {
    public static final String GET_USER_LOGOUT = "logout user";
    private final View view;
    UserService userService = new UserService();

    public GetUserLogout(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_USER_LOGOUT);
    }

    @Override
    public void execute() {
        try {
            view.write("User is logged out:\n" + userService.getUserLogout());
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
