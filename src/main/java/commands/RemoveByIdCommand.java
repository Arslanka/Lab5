package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.CYAN;
import static io.ConsoleColor.RED;

public class RemoveByIdCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public RemoveByIdCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.removeById((Integer) args[0]);
            printer.println("Элемент, значение поля id которого эквивалентно заданному, успешно удален из коллекции", CYAN);
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
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Удаляет элемент из коллекции по его id";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Integer.class};
    }
}
