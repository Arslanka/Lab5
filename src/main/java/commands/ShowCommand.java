package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.RED;

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
        printer.println(SEPARATOR, RED);
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
        return "Выводит все элементы коллекции";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }
}
