package commands;

import collection.CollectionManager;

public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        collectionManager.info();
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
