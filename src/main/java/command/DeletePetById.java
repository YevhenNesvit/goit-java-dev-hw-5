package command;

import services.PetService;
import utils.PetUtils;
import view.View;

import java.io.IOException;

public class DeletePetById implements Command {
    public static final String DELETE_PET_BY_ID = "delete pet by id";
    private final View view;
    PetUtils petUtils = new PetUtils();
    PetService petService = new PetService();

    public DeletePetById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_PET_BY_ID);
    }

    @Override
    public void execute() {
        long id;
        try {
            while (true) {
                try {
                    view.write("Please, enter pet id to delete: ");
                    id = Integer.parseInt(view.read());
                    if (petUtils.IsPetExists(id)) {
                        petService.deletePetById(id);
                        view.write("Pet with id " + id + " successfully deleted");
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
