package command;

import services.StoreService;
import view.View;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PostOrder implements Command {
    public static final String POST_ORDER = "add order";
    private final View view;
    StoreService storeService = new StoreService();

    public PostOrder(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(POST_ORDER);
    }

    @Override
    public void execute() {
        int id;
        int petId;
        int quantity;
        String shipDate;
        String status;
        boolean complete;

        try {
            while (true) {
                try {
                    view.write("Please, enter id of new order: ");
                    id = Integer.parseInt(view.read());
                    view.write("Please, enter petId of new order: ");
                    petId = Integer.parseInt(view.read());
                    view.write("Please, enter quantity of pets of new order: ");
                    quantity = Integer.parseInt(view.read());
                    TimeZone tz = TimeZone.getTimeZone("UTC");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
                    df.setTimeZone(tz);
                    shipDate = df.format(new Date());
                    view.write("Please, enter status of new order (placed, approved or delivered): ");
                    status = view.read();
                    view.write("Please, enter true if order is completed or false if not: ");
                    complete = Boolean.parseBoolean(view.read());
                    break;
                } catch (NumberFormatException e) {
                    view.write("Invalid value");
                }
            }
            storeService.addOrder(id, petId, quantity, shipDate, status, complete);
            view.write("Order with id " + id + " successfully added");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
