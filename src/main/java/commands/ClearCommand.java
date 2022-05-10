package commands;

import collection.Collection;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class ClearCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public ClearCommand(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        collection.clear();
        printer.println("The collection has been cleared", HELP);
        printer.println(SEPARATOR, ERROR);
        return true;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Clears the collection";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }


}
