package command;

import services.StoreService;
import view.View;

import java.io.IOException;

public class GetPetsStatuses implements Command {
    public static final String GET_PETS_STATUSES = "pets statuses";
    private final View view;
    StoreService storeService = new StoreService();

    public GetPetsStatuses(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PETS_STATUSES);
    }

    @Override
    public void execute() {
        while (true) {
            try {
                view.write("Pet statuses are:\n" + storeService.getPetStatusesList());
            } catch (IOException e) {
                e.getStackTrace();
            }
            break;
        }
    }
}
