package command;

import models.Tag;
import services.PetService;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostPet implements Command {
    public static final String POST_PET = "add pet";
    private final View view;
    PetService petService = new PetService();

    public PostPet(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(POST_PET);
    }

    @Override
    public void execute() {
        long id;
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
                    view.write("Please, enter id of new pet: ");
                    id = Integer.parseInt(view.read());
                    view.write("Please, enter categoryId of new pet: ");
                    categoryId = Integer.parseInt(view.read());
                    view.write("Please, enter categoryName of new pet: ");
                    categoryName = view.read();
                    while (true) {
                        view.write("Please, enter name of new pet: ");
                        String n = view.read();
                        if (!n.equals("")) {
                            name = n;
                            break;
                        } else {
                            System.out.println("Name can not be empty");
                        }
                    }
                    view.write("Please, enter photoUrl(s) of new pet: ");
                    do {
                        urlList.add(view.read());
                        System.out.print("Another one (y/n)?: ");
                        if (view.read().equalsIgnoreCase("n")) {
                            urlRun = false;
                        }
                    } while (urlRun);
                    photoUrls = urlList.toArray(new String[0]);
                    view.write("Please, enter tag(s) of new pet: ");
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
                    view.write("Please, enter status of new pet (available, pending or sold): ");
                    status = view.read();
                    break;
                } catch (NumberFormatException e) {
                    view.write("Invalid value");
                }
            }
            petService.addPet(id, categoryId, categoryName, name, photoUrls, tags, status);
            view.write("Pet with id " + id + " successfully added");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
