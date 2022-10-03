package command;

import services.UserService;
import view.View;

import java.io.IOException;

public class PostUser implements Command {
    public static final String POST_USER = "add user";
    private final View view;
    UserService userService = new UserService();

    public PostUser(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(POST_USER);
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
                    break;
                } catch (NumberFormatException e) {
                    view.write("Invalid value");
                }
            }
            userService.addUser(id, username, firstName, lastName, email, password, phone, userStatus);
            view.write("User with id " + id + " successfully added");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
