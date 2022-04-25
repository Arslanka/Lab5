package commands;

import collection.CollectionManager;

import static io.ConsoleColor.RED;

public class AddIfMaxCommand implements Command {
    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        try {
            collectionManager.addIfMax(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(RED.wrapped("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз"));
        } catch (IllegalArgumentException e) {
            System.out.println(RED.wrapped(e.getMessage()));
        }
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
