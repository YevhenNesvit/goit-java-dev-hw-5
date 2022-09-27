package view;

import command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements View {
    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    public List<Command> commands() {
        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new PostPet(view));
        commands.add(new PutPet(view));
        commands.add(new GetPetsByStatus(view));
        commands.add(new GetPetById(view));
        commands.add(new PostPetById(view));
        commands.add(new Exit(view));

        return commands;
    }
}
