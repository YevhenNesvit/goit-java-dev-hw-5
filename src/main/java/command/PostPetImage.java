package command;

import services.PetService;
import utils.PetUtils;
import view.View;

import java.io.IOException;
import java.sql.SQLException;

public class PostPetImage implements Command {
    public static final String ADD_IMAGE = "add pet image";
    private final View view;
    PetUtils petUtils = new PetUtils();
    PetService petService = new PetService();

    public PostPetImage(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_IMAGE);
    }

    @Override
    public void execute() {
        long id;
        String filPath;
        try {
            while (true) {
                try {
                    view.write("Please, enter file path to upload: ");
                    filPath = view.read();
                    view.write("Please, enter pet id to upload image: ");
                    id = Integer.parseInt(view.read());
                    if (petUtils.IsPetExists(id)) {
                        petService.uploadFile(id, filPath);
                        view.write("Image for pet with id " + id + " successfully uploaded");
                        break;
                    } else {
                        System.out.println("Pet id doesn't exists");
                    }
                } catch (NumberFormatException e) {
                    view.write("Invalid value. Use digits");
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
