package commands;

import io.ConsoleColor;
import io.Printer;

import java.util.HashMap;
import java.util.Map;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.CYAN;
import static io.ConsoleColor.RED;

public class HelpCommand implements Command {
    private final Printer printer;

    public HelpCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        Map<String, Command> commandMap = (Map<String, Command>) args[0];

        for (String entry : commandMap.keySet()) {
            printer.println((String.format("%-30s", entry) + " "  + commandMap.get(entry).getDescription()), CYAN);
        }
        printer.println(SEPARATOR, RED);
        return true;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Выводит названия и описания всех команд";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }

}