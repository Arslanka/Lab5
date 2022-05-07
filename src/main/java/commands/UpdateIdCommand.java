package commands;

import collection.Collection;
import data.Dragon;
import io.Printer;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class UpdateIdCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public UpdateIdCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.updateId((Integer) args[0], (Dragon) args[1]);
            printer.println("Элемент коллекции с id" + args[0] + " успешно обновлен", HELP);
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
        return "update";
    }

    @Override
    public String getDescription() {
        return "Обновляет значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Integer.class, Dragon.class};
    }
}
