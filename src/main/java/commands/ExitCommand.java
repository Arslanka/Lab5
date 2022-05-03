package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.RED;

public class ExitCommand implements Command {
    private final Printer printer;

    public ExitCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        printer.println("Программа завершена", RED);
        return false;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public String getDescription() {
        return "Останавливает выполнение программы";
    }

    @Override
    public Class[] getArgumentsClasses() {
        return new Class[]{};
    }
}
