package services;

import converter.PetToJSONConverter;
import models.Pet;
import models.Tag;
import utils.PetUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class PetService {
    private static final String PET = "https://petstore.swagger.io/v2/pet/";
    private static final String PETS_BY_STATUS = "https://petstore.swagger.io/v2/pet/findByStatus";
    PetToJSONConverter converter = new PetToJSONConverter();
    PetUtils petUtils = new PetUtils();

    public void addPet(Long id, Integer categoryId, String categoryName, String name, String[] photoUrls,
                       Tag[] tags, String status) throws IOException {
        converter.petToJson(id, categoryId, categoryName, name, photoUrls, tags, status);
        URL url = new URL(PET);
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

    public void updatePet(Long id, Integer categoryId, String categoryName, String name, String[] photoUrls,
                       Tag[] tags, String status) throws IOException {
        converter.petToJson(id, categoryId, categoryName, name, photoUrls, tags, status);
        URL url = new URL(PET);
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

    public List<Pet> getPetsByStatus(String status) throws IOException {
        URL url = new URL(String.format(PETS_BY_STATUS + "?status=%s", status));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request not worked");
        }
        return petUtils.getMultiplePets(response);
    }

    public Pet getPetById(Integer id) throws IOException {
        URL url = new URL(String.format(PET + id));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request not worked");
        }
        return petUtils.petToObj(response);
    }
}
