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
        view.write(String.format("Enter %s to view pets statuses", GetPetsStatuses.GET_PETS_STATUSES));
        view.write(String.format("Enter %s to add users with array", PostUsersWithArray.POST_USER_WITH_ARRAY));
        view.write(String.format("Enter %s to add users with list", PostUsersWithList.POST_USER_WITH_LIST));
        view.write(String.format("Enter %s to view user by username", GetUserByUsername.GET_USER_BY_USERNAME));
        view.write(String.format("Enter %s to update user by username", PutUserByUsername.PUT_USER_BY_USERNAME));
        view.write(String.format("Enter %s to delete user by username", DeleteUserByUsername.DELETE_USER_BY_USERNAME));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
    }
}
