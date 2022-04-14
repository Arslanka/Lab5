package collection;

import data.Dragon;
import file.JsonFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class CollectionManager {
    private final HashSet<Dragon> dragonHashSet = new HashSet<>();

    private final java.time.LocalDateTime creationDate;


    public CollectionManager(JsonFile jsonFile) throws IOException {
        List<Dragon> dragonList = jsonFile.read();
        dragonHashSet.addAll(dragonList);
        this.creationDate = java.time.LocalDateTime.now();
    }

    public boolean add(Dragon dragon) {
        return dragonHashSet.add(dragon);
    }
}
