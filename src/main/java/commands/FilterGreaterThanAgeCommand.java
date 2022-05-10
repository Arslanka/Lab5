package commands;

import collection.Collection;
import exceptions.ExecutionException;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.ERROR;

public class FilterGreaterThanAgeCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public FilterGreaterThanAgeCommand(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Long age = (Long) args[0];
            collection.filterGreaterThanAge(age);
            printer.println(SEPARATOR, ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ExecutionException("You have not entered an element to filter by collection.");
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "filter_greater_than_age";
    }

    @Override
    public String getDescription() {
        return "Outputs elements whose age field value is greater than the specified one";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Integer.class};
    }
}
