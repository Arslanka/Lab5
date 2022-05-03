package collection;

import data.Dragon;
import data.Person;
import file.JsonFile;
import io.Printer;
import io.request.RequestDragon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static io.ConsoleColor.*;

public class Collection {
    private final HashSet<Dragon> dragonHashSet = new HashSet<>();
    private final Printer printer = new Printer();
    public static final TreeMap<Object, Dragon> idMap = new TreeMap<>();
    private final LocalDateTime creationDate = LocalDateTime.now();

    public Collection() {
    }

    public void add(Dragon dragon) {
        Object dragonId = dragon.getId();
        try {
            if (dragon.isValid() != null)
                if (idMap.containsKey(dragonId))
                    throw new IllegalArgumentException("В коллекции уже есть объект с id " + dragonId + ". Пожалуйста, введите другое id");
            idMap.put(dragonId, dragon);
            dragonHashSet.add(dragon);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void add(java.util.Collection<Dragon> dragonList) {
        int dragonCount = 0; //Todo change
        for (Dragon d : dragonList) {
            try {
                if (d.isValid() != null) { //Todo fix
                    ++dragonCount;
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage() + '\n' + ((dragonCount + 1) + " элемент файла не валиден"));
            }
        }
        dragonList.forEach(this::add);
    }

    public void show() {
        printer.printCollection(dragonHashSet, BLUE);
    }

    public void updateId(Object id, Object obj) { //
        if (!idMap.containsKey(id)) throw new IllegalArgumentException("Вы ввели некорректный id.");
        dragonHashSet.remove(idMap.get(id));
        Dragon curDragon;
        try {
            RequestDragon requestDragon = (RequestDragon) obj;
            requestDragon.get().build();
        } catch (ClassCastException ignored) {
        }
        curDragon = (Dragon) obj;
        curDragon.setId((Integer) id);
        idMap.put(id, curDragon);
        dragonHashSet.add(curDragon);
    }

    public void removeById(Integer id) {
        if (!idMap.containsKey(id))
            throw new IllegalArgumentException("Такого id нет в коллекции. Исполните команду с корректным id.");
        dragonHashSet.remove(idMap.get(id));
        idMap.remove(id);
    }

    public void clear() {
        dragonHashSet.clear();
    }

    public void save(JsonFile jsonFile) throws IOException {
        jsonFile.write(dragonHashSet);
    }

    public void removeLower(Dragon dragon) {
        dragonHashSet.removeIf(d -> d.compareTo(dragon) < 0);
        idMap
                .keySet()
                .removeIf(id -> idMap.get(id).compareTo(dragon) < 0);
    }

    public void removeGreater(Dragon dragon) {
        dragonHashSet.removeIf(d -> d.compareTo(dragon) > 0);
        idMap
                .keySet()
                .removeIf(id -> idMap.get(id).compareTo(dragon) > 0);
    }

    public void removeByWeight(Float weight) {
        dragonHashSet.removeIf(d -> (Objects.equals(d.getWeight(), weight)));
        idMap
                .keySet()
                .removeIf(id -> Objects.equals(idMap.get(id).getWeight(), weight));
    }


    public void info() {
        String collectionName = "HashSet<Dragon> dragonHashSet";
        printer.println(String.format("%-30s", "Тип коллекции: ") + " " + collectionName, CYAN);
        printer.println(String.format("%-30s", "Дата и время создания коллекции ") + " " + creationDate, CYAN);
        printer.println(String.format("%-30s", "Размер коллекции: ") + " " + dragonHashSet.size(), CYAN);
    }

    public void filterGreaterThanAge(Long age) {
        printer.println("Элементы коллекции, у которых поле age больше заданного: ", CYAN); //TODO stream API

        dragonHashSet
                .stream()
                // TODO: error comparison
                .filter(d -> age.compareTo(d.getAge()) < 0)
                .forEach(d -> printer.println(d, BLUE));
    }

    public void countGreaterThanKiller(Object personObj) {
        Person killer = (Person) personObj;
        long cnt = dragonHashSet
                .stream()
                .filter(d -> d.getKiller().compareTo(killer) > 0)
                .count();
        printer.println("Элементов коллекции, у которых поле killer больше заданного – " + cnt, CYAN);
    }

    public void addIfMax(Dragon dragon) {
        if (dragon.compareTo(Collections.max(dragonHashSet)) > 0) {
            dragonHashSet.add(dragon);
        }
    }

    public static Integer mex() {
        if (idMap.isEmpty()) return 1;
        for (int i = 1; i <= (Integer) idMap.lastKey() + 1; ++i) {
            if (!idMap.containsKey(i))
                return i;
        }
        return (Integer) idMap.lastKey() + 1;
    }
}

