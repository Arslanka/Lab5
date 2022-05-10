package commands;

import collection.Collection;
import exceptions.ExecutionException;
import exceptions.IncorrectIdException;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class RemoveByIdCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public RemoveByIdCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.removeById((Integer) args[0]);
            printer.println("An element whose id field value is equivalent to the specified " + args[0] + " one has been successfully removed from the collection", HELP);
            printer.println(SEPARATOR, ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ExecutionException("You have not entered an id to remove from the collection.");
        } catch (IncorrectIdException e) {
            throw new ExecutionException("Deleting an element by id error.\n" + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Removes an item from the collection by its id";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Integer.class};
    }
}
