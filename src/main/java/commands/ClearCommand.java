package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.CYAN;
import static io.ConsoleColor.RED;

public class ClearCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public ClearCommand(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        collection.clear();
        printer.println("Коллекция очищена", CYAN);
        printer.println(SEPARATOR, RED);
        return true;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Очищает коллекцию";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }


}
