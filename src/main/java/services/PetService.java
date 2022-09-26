package services;

import converter.PetToJSONConverter;
import models.Tag;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class PetService {
    private static final String ADD_PET = "https://petstore.swagger.io/v2/pet/";
    PetToJSONConverter converter = new PetToJSONConverter();

    public void addPet(Integer id, Integer categoryId, String categoryName, String name, String[] photoUrls,
                       Tag[] tags, String status) throws IOException {
        converter.petToJson(id, categoryId, categoryName, name, photoUrls, tags, status);
        URL url = new URL(ADD_PET);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        OutputStream os = connection.getOutputStream();
        os.write(Files.readAllBytes(new File("src" + File.separator + "main" + File.separator + "resources"
                + File.separator + "TemporaryPet.json").toPath()));
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
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

    public void updatePet(Integer id, Integer categoryId, String categoryName, String name, String[] photoUrls,
                       Tag[] tags, String status) throws IOException {
        converter.petToJson(id, categoryId, categoryName, name, photoUrls, tags, status);
        URL url = new URL(ADD_PET);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        OutputStream os = connection.getOutputStream();
        os.write(Files.readAllBytes(new File("src" + File.separator + "main" + File.separator + "resources"
                + File.separator + "TemporaryPet.json").toPath()));
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("PUT response code: " + responseCode);
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
            System.out.println("PUT request not worked");
        }
    }
}
