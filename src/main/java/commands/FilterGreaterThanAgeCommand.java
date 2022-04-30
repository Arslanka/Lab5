package commands;

import collection.CollectionManager;

import static io.ConsoleColor.RED;

public class FilterGreaterThanAgeCommand implements Command {
    private final CollectionManager collectionManager;

    public FilterGreaterThanAgeCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        try {
            collectionManager.filterGreaterThanAge(args[0]);
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
        return "filter_greater_than_age";
    }

    @Override
    public String getDescription() {
        return "Выводит элементы, значение поля age которых больше заданного";
    }
}
