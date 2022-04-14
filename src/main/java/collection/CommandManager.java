package collection;


import file.JsonFile;

public class CommandManager {
    private final JsonFile jsonFile;
    private final CollectionManager collectionManager;

    public CommandManager(JsonFile jsonFile, CollectionManager collectionManager) {
        this.jsonFile = jsonFile;
        this.collectionManager = collectionManager;
    }


}