package collection;


import file.JsonFile;
import io.RequestElement;

public class CommandManager {
    private final CollectionManager collectionManager;

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public boolean executeCommand(RequestElement command, CollectionManager co) {
        return true;
    }
}