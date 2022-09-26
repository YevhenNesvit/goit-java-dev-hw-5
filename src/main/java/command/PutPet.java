package command;

import models.Tag;
import services.PetService;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutPet implements Command {
    public static final String UPDATE_PET = "update pet";
    private final View view;
    PetService petService = new PetService();

    public PutPet(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PET);
    }

    @Override
    public void execute() {
        int id;
        int categoryId;
        String categoryName;
        String name;
        String[] photoUrls;
        Tag[] tags;
        String status;

        try {
            while (true) {
                List<String> urlList = new ArrayList<>();
                List<Tag> tagList = new ArrayList<>();
                boolean urlRun = true;
                boolean tagRun = true;
                int tagId;
                String tagName;
                try {
                    view.write("Please, enter new id of pet to update: ");
                    id = Integer.parseInt(view.read());
                    view.write("Please, enter new categoryId of pet to update: ");
                    categoryId = Integer.parseInt(view.read());
                    view.write("Please, enter new categoryName of pet to update: ");
                    categoryName = view.read();
                    while (true) {
                        view.write("Please, enter new name of pet to update: ");
                        String n = view.read();
                        if (!n.equals("")) {
                            name = n;
                            break;
                        } else {
                            System.out.println("Name can not be empty");
                        }
                    }
                    view.write("Please, enter new photoUrl(s) of pet to update: ");
                    do {
                        urlList.add(view.read());
                        System.out.print("Another one (y/n)?: ");
                        if (view.read().equalsIgnoreCase("n")) {
                            urlRun = false;
                        }
                    } while (urlRun);
                    photoUrls = urlList.toArray(new String[0]);
                    view.write("Please, enter new tag(s) of pet to update: ");
                    do {
                        view.write("Enter tagId first: ");
                        tagId = Integer.parseInt(view.read());
                        view.write("Then enter tagName: ");
                        tagName = view.read();
                        tagList.add(new Tag(tagId, tagName));
                        System.out.print("Another one (y/n)?: ");
                        if (view.read().equalsIgnoreCase("n")) {
                            tagRun = false;
                        }
                    } while (tagRun);
                    tags = tagList.toArray(new Tag[0]);
                    view.write("Please, enter new status of pet to update (available, pending or sold): ");
                    status = view.read();
                    break;
                } catch (NumberFormatException e) {
                    view.write("Invalid value");
                }
            }
            petService.updatePet(id, categoryId, categoryName, name, photoUrls, tags, status);
            view.write("Pet with id " + id + " successfully updated");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
