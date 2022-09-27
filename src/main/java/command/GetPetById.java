package command;

import services.PetService;
import view.View;

import java.io.IOException;

public class GetPetById implements Command {
    public static final String GET_PET_BY_ID = "pet by id";
    private final View view;
    PetService petService = new PetService();
    public GetPetById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PET_BY_ID);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter pet id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For id " + id + " pet is:\n" + petService.getPetById(id));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
