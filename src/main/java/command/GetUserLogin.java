package command;

import services.UserService;
import view.View;

import java.io.IOException;

public class GetUserLogin implements Command {
    public static final String GET_USER_LOGIN = "login user";
    private final View view;
    UserService userService = new UserService();

    public GetUserLogin(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_USER_LOGIN);
    }

    @Override
    public void execute() {
        String username;
        String password;
        while (true) {
            view.write("Please, enter username: ");
            String u = view.read();
            if (!u.equals("")) {
                username = u;
                break;
            } else {
                System.out.println("Name can not be empty");
            }
        }
        while (true) {
            view.write("Please, enter password: ");
            String p = view.read();
            if (!p.equals("")) {
                password = p;
                break;
            } else {
                System.out.println("Password can not be empty");
            }
        }
        try {
            view.write("User with username " + username + " is logged\n" + userService.getUserLogin(username, password));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
