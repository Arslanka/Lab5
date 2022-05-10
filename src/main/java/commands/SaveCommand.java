package commands;

import collection.Collection;
import exceptions.ExecutionException;
import file.JsonFile;
import io.Printer;

import java.io.File;
import java.io.IOException;

import static io.Application.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class SaveCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public SaveCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.save((JsonFile) args[0]);
            printer.println("The collection is saved to a file " + args[0], HELP);
            printer.println(SEPARATOR, ERROR);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new ExecutionException("You have not entered an JsonFile.");
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Saves the collection to a file";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{File.class};
    }
}
