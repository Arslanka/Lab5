package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.RED;

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
            printer.println(SEPARATOR, RED);
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
        return "filter_greater_than_age";
    }

    @Override
    public String getDescription() {
        return "Выводит элементы, значение поля age которых больше заданного";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Integer.class};
    }
}
