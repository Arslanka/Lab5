package commands;

import collection.Collection;
import data.Dragon;
import data.Person;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.CYAN;
import static io.ConsoleColor.RED;

public class RemoveLowerCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public RemoveLowerCommand(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.removeLower((Dragon) args[0]);
            printer.println("Элементы, значение которых меньше заданного, успешно удалены из коллекции", CYAN);
            printer.println(SEPARATOR, RED);
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
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "Удаляет из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Dragon.class};
    }
}
