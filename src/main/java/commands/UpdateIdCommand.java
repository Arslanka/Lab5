package commands;

import collection.Collection;
import data.Dragon;
import exceptions.ExecutionException;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class UpdateIdCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public UpdateIdCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.updateId((Integer) args[0], (Dragon) args[1]);
            printer.println("Collection item with id " + args[0] + " successfully updated ", HELP);
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
        return "update";
    }

    @Override
    public String getDescription() {
        return "Updates the value of a collection item whose id is equal to the specified one";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Integer.class, Dragon.class};
    }
}
