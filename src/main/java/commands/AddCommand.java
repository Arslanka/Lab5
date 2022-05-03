package commands;

import collection.Collection;
import data.Dragon;
import io.*;
import io.Printer;

import static io.Console.SEPARATOR;

import static io.ConsoleColor.CYAN;
import static io.ConsoleColor.RED;

public class AddCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public AddCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Dragon dragon = (Dragon) args[0];
            collection.add(dragon);
            printer.println("Элемент успешно добавлен в коллекцию", CYAN);
            printer.println(SEPARATOR, RED);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз");
        }
        return true;
    }


    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Добавляет элемент в коллекцию";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{Dragon.class};
    }

}
