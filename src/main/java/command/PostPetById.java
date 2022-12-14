package command;

import services.PetService;
import utils.PetUtils;
import view.View;

import java.io.IOException;

public class PostPetById implements Command {
    public static final String POST_PET_BY_ID = "update pet by id";
    private final View view;
    PetService petService = new PetService();
    PetUtils petUtils = new PetUtils();

    public PostPetById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(POST_PET_BY_ID);
    }

    @Override
    public void execute() {
        String name;
        String status;
        long id;
        try {
            while (true) {
                try {
                    while (true) {
                        view.write("Please, enter name to update: ");
                        String n = view.read();
                        if (!n.equals("")) {
                            name = n;
                            break;
                        } else {
                            System.out.println("Name can not be empty");
                        }
                    }
                    view.write("Please, enter status to update (available, pending or sold): ");
                    status = view.read();
                    view.write("Please, enter pet id: ");
                    id = Integer.parseInt(view.read());
                    if (petUtils.IsPetExists(id)) {
                        petService.updatePetById(id, name, status);
                        view.write("Pet with id " + id + " successfully updated");
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
