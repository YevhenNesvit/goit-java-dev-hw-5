package command;

import services.PetService;
import view.View;

import java.io.IOException;

public class GetPetsByStatus implements Command {
    public static final String GET_PETS_BY_STATUS = "pets by status";
    private final View view;
    PetService petService = new PetService();

    public GetPetsByStatus(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PETS_BY_STATUS);
    }

    @Override
    public void execute() {
        String status;
        view.write("Please, enter status of pet like available, pending or sold: ");
        while (true) {
            status = view.read();
            if (status.equals("available") || status.equals("pending") || status.equals("sold")) {
                try {
                    view.write("For status " + status + " list of pets is:\n" + petService.getPetsByStatus(status));
                } catch (IOException e) {
                    e.getStackTrace();
                }
                break;
            } else {
                view.write("Invalid value. Please enter available, pending, sold");
            }
        }
    }
}
