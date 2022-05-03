package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.RED;

public class InfoCommand implements Command {
    private final Collection collection;
    private final Printer printer;
    public InfoCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        collection.info();
        printer.println(SEPARATOR, RED);
        return true;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Выводит информацию о коллекции";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }
}
