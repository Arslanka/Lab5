package commands;

import collection.Collection;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.ERROR;

public class ShowCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public ShowCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        collection.show();
        printer.println(SEPARATOR, ERROR);
        return true;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Outputs all elements of the collection";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }
}
