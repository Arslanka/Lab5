package commands;

import collection.CollectionManager;

import static io.ConsoleColor.RED;

public class CountGreaterThanKillerCommand implements Command {
    private final CollectionManager collectionManager;

    public CountGreaterThanKillerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        try {
            collectionManager.countGreaterThanKiller(args[0]);
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

    @Override
    public String getName() {
        return "count_greater_than_killer";
    }

    @Override
    public String getDescription() {
        return "Выводит количество элементов, значение поля killer которых больше заданного";
    }
}
