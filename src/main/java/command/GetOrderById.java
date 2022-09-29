package command;

import services.StoreService;
import view.View;

import java.io.IOException;

public class GetOrderById implements Command {
    public static final String GET_ORDER_BY_ID = "order by id";
    private final View view;
    StoreService storeService = new StoreService();

    public GetOrderById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_ORDER_BY_ID);
    }

    @Override
    public void execute() {
        long id;
        while (true) {
            try {
                view.write("Please, enter order id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For id " + id + " order is:\n" + storeService.getOrderById(id));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
