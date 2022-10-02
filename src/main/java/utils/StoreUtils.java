package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Order;
import services.StoreService;

import java.io.IOException;
import java.util.Map;

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

    public Order orderToObj(StringBuffer response) {
        String orderToObj = String.valueOf(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(orderToObj, Order.class);
    }

    public boolean IsOrderExists(Long id) throws IOException {
        StoreService storeService = new StoreService();
        return storeService.getOrderById(id) != null;
    }

    public Map<String, Integer> petsStatusesToObj(StringBuffer response) {
        String petStatusesToObj = String.valueOf(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(petStatusesToObj, Map.class);
    }
}
