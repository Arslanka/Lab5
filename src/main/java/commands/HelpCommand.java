package commands;

import io.ConsoleColor;
import io.Printer;

import java.util.HashMap;

import static io.ConsoleColor.CYAN;

public class HelpCommand implements Command {
    private final Printer printer;

    public HelpCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(Object... args) {
        HashMap<String, Command> commandMap = (HashMap<String, Command>) args[0];
        for (String entry : commandMap.keySet()) {
            printer.print(String.format("%s\t|\t%s\n", entry, commandMap.get(entry).getDescription()), CYAN);
        }
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Выводит названия и описания всех команд";
    }

}