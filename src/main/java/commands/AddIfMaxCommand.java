package commands;

import collection.Collection;
import data.Dragon;
import exceptions.ExecutionException;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class AddIfMaxCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public AddIfMaxCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Dragon dragon = (Dragon) args[0];
            collection.addIfMax(dragon);
            printer.println("The item was successfully added to the collection", HELP);
            printer.println(SEPARATOR, ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ExecutionException("You have not entered an item to add to the collection.");
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "Adds a new element to the collection if its value exceeds the value of the largest element of this collection";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Dragon.class};
    }
}
