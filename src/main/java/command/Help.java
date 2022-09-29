package command;

import view.View;

public class Help implements Command {
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all commands", Help.HELP));
        view.write(String.format("Enter %s to add pet", PostPet.POST_PET));
        view.write(String.format("Enter %s to update pet", PutPet.PUT_PET));
        view.write(String.format("Enter %s to view pets by status", GetPetsByStatus.GET_PETS_BY_STATUS));
        view.write(String.format("Enter %s to view pet by id", GetPetById.GET_PET_BY_ID));
        view.write(String.format("Enter %s to update pet by id", PostPetById.POST_PET_BY_ID));
        view.write(String.format("Enter %s to delete pet by id", DeletePetById.DELETE_PET_BY_ID));
        view.write(String.format("Enter %s to upload pet image", PostPetImage.ADD_IMAGE));
        view.write(String.format("Enter %s to add order", PostOrder.POST_ORDER));
        view.write(String.format("Enter %s to view order by id", GetOrderById.GET_ORDER_BY_ID));
        view.write(String.format("Enter %s to delete order by id", DeleteOrderById.DELETE_ORDER));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
    }
}
