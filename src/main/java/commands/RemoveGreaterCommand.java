package commands;

import collection.Collection;
import data.Dragon;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class RemoveGreaterCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public RemoveGreaterCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.removeGreater((Dragon) args[0]);
            printer.println("Элементы, значение которых больше заданного, успешно удалены из коллекции", HELP);
            printer.println(SEPARATOR, ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз");
        } catch (IllegalArgumentException e) {
            throw  new IllegalArgumentException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "Удаляет из коллекции все элементы, превышающие заданный";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Dragon.class};
    }
}
