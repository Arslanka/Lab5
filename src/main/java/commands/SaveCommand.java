package commands;

import collection.Collection;
import file.JsonFile;
import io.Printer;

import java.io.File;
import java.io.IOException;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.HELP;
import static io.ConsoleColor.ERROR;

public class SaveCommand implements Command {
    private final Collection collection;
    private final Printer printer;

    public SaveCommand(Collection collection, Printer printer) {

        this.collection = collection;
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            collection.save((JsonFile) args[0]);
            printer.println("Коллекция сохранена в файл " + args[0], HELP);
            printer.println(SEPARATOR, ERROR);

        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            throw new IllegalArgumentException("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз");
        } catch (NullPointerException e){
            throw new IllegalStateException("");
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Сохраняет коллекцию в файл";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{File.class};
    }
}
