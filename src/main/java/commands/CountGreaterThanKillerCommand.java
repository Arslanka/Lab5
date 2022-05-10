package commands;

import collection.Collection;
import data.Person;
import exceptions.ExecutionException;
import io.Printer;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.ERROR;

public class CountGreaterThanKillerCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public CountGreaterThanKillerCommand(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Person person = (Person) args[0];
            collection.countGreaterThanKiller(person);
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
        return "count_greater_than_killer";
    }

    @Override
    public String getDescription() {
        return "Outputs the number of elements whose killer field value is greater than the specified one";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Person.class};
    }
}
