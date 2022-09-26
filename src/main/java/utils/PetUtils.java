package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Pet;

import java.lang.reflect.Type;
import java.util.List;

public class PetUtils {

    public List<Pet> getMultiplePets(StringBuffer response) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Pet>>() {
        }.getType();

        return gson.fromJson(String.valueOf(response), type);
    }
}
