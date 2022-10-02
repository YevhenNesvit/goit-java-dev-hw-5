package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Category;
import models.Pet;
import models.Tag;
import services.PetService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PetUtils {

    public List<Pet> getMultiplePets(StringBuffer response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<Pet>>() {
        }.getType();

        return gson.fromJson(String.valueOf(response), type);
    }

    public Pet petToObj(StringBuffer response) {
        String petToObj = String.valueOf(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(petToObj, Pet.class);
    }

    public String petToString(Long id, Integer categoryId, String categoryName, String name, String[] photoUrls,
                              Tag[] tags, String status) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        Pet pet = new Pet();
        pet.setId(id);
        pet.setCategory(category);
        pet.setName(name);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus(status);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(pet);
    }

    public String petPartialUpdate(Long id, String name, String status) throws IOException {
        PetService petService = new PetService();

        Pet pet = petService.getPetById(id);
        pet.setName(name);
        pet.setStatus(status);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(pet);
    }

    public boolean IsPetExists(Long id) throws IOException {
        PetService petService = new PetService();
        return petService.getPetById(id) != null;
    }
}
