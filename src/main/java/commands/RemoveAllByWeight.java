package commands;

import collection.Collection;
import exceptions.ExecutionException;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class RemoveAllByWeight implements Command {
    private final Collection collection;
    private final Printer printer;

    public RemoveAllByWeight(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Float weight = (Float) args[0];
            collection.removeByWeight(weight);
            printer.println(String.format("%s %f %s", "Elements whose weight field value is equivalent to", weight, "the specified one have been successfully removed from the collection"), HELP);
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
        return "remove_all_by_weight";
    }

    @Override
    public String getDescription() {
        return "Removes from the collection all elements whose weight field value is equivalent to the specified";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Float.class};
    }
}
