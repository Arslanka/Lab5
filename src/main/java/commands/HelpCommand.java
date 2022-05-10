package commands;

import io.Printer;

import java.util.Map;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class HelpCommand implements Command {
    private final Printer printer;

    public HelpCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        Map<String, Command> commandMap = (Map<String, Command>) args[0];

        for (String entry : commandMap.keySet()) {
            printer.println((String.format("%-30s", entry) + " "  + commandMap.get(entry).getDescription()), HELP);
        }
        printer.println(SEPARATOR, ERROR);
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