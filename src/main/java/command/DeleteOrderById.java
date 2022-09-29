package command;

import services.StoreService;
import utils.StoreUtils;
import view.View;

import java.io.IOException;

public class DeleteOrderById implements Command {
    public static final String DELETE_ORDER = "delete order";
    private final View view;
    StoreUtils storeUtils = new StoreUtils();
    StoreService storeService = new StoreService();

    public DeleteOrderById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_ORDER);
    }

    @Override
    public void execute() {
        long id;
        try {
            while (true) {
                try {
                    view.write("Please, enter order id to delete: ");
                    id = Integer.parseInt(view.read());
                    if (storeUtils.IsOrderExists(id)) {
                        storeService.deleteOrderById(id);
                        view.write("Order with id " + id + " successfully deleted");
                        break;
                    } else {
                        System.out.println("Order id doesn't exists");
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
