package commands;

import collection.Collection;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.ERROR;

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
        printer.println(SEPARATOR, ERROR);
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
        return "Displays information about the collection";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }
}
