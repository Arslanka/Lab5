package commands;

import collection.Collection;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class RemoveAllByWeight implements Command {
    private final Collection collection;
    private final Printer printer;

    public RemoveAllByWeight(Collection collection, Printer printer) {
        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Float weight = (Float) args[0];
            collection.removeByWeight(weight);
            printer.println("Элементы, значение поля weight которых эквивалентно заданному, успешно удалены из коллекции", HELP);
            printer.println(SEPARATOR, ERROR);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw  new IllegalArgumentException("Вы не ввели элемент, который необходимо добавить в коллекцию." +
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
        return "remove_all_by_weight";
    }

    @Override
    public String getDescription() {
        return "Удаляет из коллекции все элементы, значение поля weight которых эквивалентно заданному";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Float.class};
    }
}
