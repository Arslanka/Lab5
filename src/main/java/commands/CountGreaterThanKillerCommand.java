package commands;

import collection.Collection;
import data.Person;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.RED;

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
        return "count_greater_than_killer";
    }

    @Override
    public String getDescription() {
        return "Выводит количество элементов, значение поля killer которых больше заданного";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Person.class};
    }
}
