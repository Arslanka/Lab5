package commands;

import collection.CollectionManager;
import data.Dragon;

public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        try {
            Dragon dragon = (Dragon) args[0];
            collectionManager.add(dragon);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Вы не ввели элемент, который необходимо добавить в коллекцию." +
                    " Пожалуйста, попробуйте еще раз");
        }
    }


    @Override
    public boolean withArgument() {
        return true;
    }

}
