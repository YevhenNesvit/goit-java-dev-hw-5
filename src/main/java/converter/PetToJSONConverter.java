package converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Category;
import models.Pet;
import models.Tag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PetToJSONConverter {

    public void petToJson(Long id, Integer categoryId, String categoryName, String name, String[] photoUrls,
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

        final File OUTPUT_FILE_PATH = new File("src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "TemporaryPet.json");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH.getAbsolutePath()))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String pets = gson.toJson(pet);
            bufferedWriter.write(pets);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
