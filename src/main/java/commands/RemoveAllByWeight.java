package commands;

import collection.CollectionManager;

import static io.ConsoleColor.RED;

public class RemoveAllByWeight implements Command {
    private final CollectionManager collectionManager;

    public RemoveAllByWeight(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        try {
            collectionManager.removeByWeight(args[0]);
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
        return "remove_all_by_weight";
    }

    @Override
    public String getDescription() {
        return "Удаляет из коллекции все элементы, значение поля weight которых эквивалентно заданному";
    }
}
