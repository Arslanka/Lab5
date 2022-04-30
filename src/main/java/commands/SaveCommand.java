package commands;

import collection.CollectionManager;
import file.JsonFile;

import java.io.IOException;

import static io.ConsoleColor.RED;

public class SaveCommand implements Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        try {
            collectionManager.save((JsonFile) args[0]);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println(RED.wrapped("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз"));
        }
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
}
