package services;

import utils.StoreUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoreService {
    private static final String STORE = "https://petstore.swagger.io/v2/store/order/";
    StoreUtils storeUtils = new StoreUtils();

    public void addOrder(Integer id, Integer petId, Integer quantity, String shipDate, String status, boolean complete)
            throws IOException {
        String order = storeUtils.orderToString(id, petId, quantity, shipDate, status, complete);
        URL url = new URL(STORE);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(order);

        int responseCode = connection.getResponseCode();
//        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        } else {
            System.out.println("POST request not worked");
        }
    }
}
