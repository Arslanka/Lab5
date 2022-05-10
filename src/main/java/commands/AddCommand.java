package commands;

import collection.Collection;
import data.Dragon;
import exceptions.ExecutionException;
import exceptions.ExistingIdException;
import exceptions.InvalidObjectFieldException;
import io.Printer;

import static io.Application.SEPARATOR;

import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class AddCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public AddCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) throws ExistingIdException {
        try {
            Dragon dragon = (Dragon) args[0];
            collection.add(dragon);
            printer.println("The item was successfully added to the collection", HELP);
            printer.println(SEPARATOR, ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ExecutionException("You have not entered an item to add to the collection.");
        } catch (InvalidObjectFieldException e) {
            throw new ExecutionException("Error executing the add command\n" + e.getMessage());
        }
        return true;
    }


    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Adds an item to the collection";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Dragon.class};
    }

}
