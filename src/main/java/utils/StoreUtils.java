package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Order;

public class StoreUtils {
    public String orderToString(Integer id, Integer petId, Integer quantity, String shipDate, String status, boolean complete) {

        Order order = new Order();
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(order);
    }
}
