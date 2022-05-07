package commands;

import collection.Collection;
import data.Dragon;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class AddIfMaxCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public AddIfMaxCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Dragon dragon = (Dragon) args[0];
            collection.addIfMax(dragon);
            printer.println("Элемент успешно добавлен в коллекцию", HELP);
            printer.println(SEPARATOR, ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "Добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Dragon.class};
    }
}
