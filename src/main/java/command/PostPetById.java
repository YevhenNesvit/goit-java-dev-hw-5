package command;

import services.PetService;
import view.View;

import java.io.IOException;

public class PostPetById implements Command {
    public static final String POST_PET_BY_ID = "update pet by id";
    private final View view;
    PetService petService = new PetService();

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
                    view.write("Please, enter name to update: ");
                    name = view.read();
                    view.write("Please, enter status to update (available, pending or sold): ");
                    status = view.read();
                    view.write("Please, enter pet id: ");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException e) {
                    view.write("Invalid value. Use digits");
                }
            }
            petService.updatePetById(id, name, status);
            view.write("Pet with id " + id + " successfully updated");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
