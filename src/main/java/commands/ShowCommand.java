package commands;

import collection.CollectionManager;

public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Object... args) {
        collectionManager.show();
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
