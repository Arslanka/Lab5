package collection;

import data.Dragon;
import data.Person;
import file.JsonFile;
import io.Printer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static io.ConsoleColor.*;

public class Collection {
    private final HashSet<Dragon> dragonHashSet = new HashSet<>();
    private final Printer printer = new Printer();
    public static final TreeMap<Integer, Dragon> idMap = new TreeMap<>();
    private final LocalDateTime creationDate = LocalDateTime.now();

    public Collection() {
    }

    public void add(Dragon dragon) {
        Integer dragonId = dragon.getId();
        try {
            if (dragon.validated() != null)
                if (idMap.containsKey(dragonId))
                    throw new IllegalArgumentException("В коллекции уже есть объект с id " + dragonId + ". Пожалуйста, введите другое id");
            idMap.put(dragonId, dragon);
            dragonHashSet.add(dragon);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Вы ввели некорректные поля для объекта");
        }
    }

    public void add(java.util.Collection<Dragon> dragonList) {
        int dragonCount = 0; //Todo change
        for (Dragon d : dragonList) {
            try {
                if (d.validated() != null) { //Todo fix
                    ++dragonCount;
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage() + '\n' + ((dragonCount + 1) + " элемент файла не валиден"));
            }
        }
        dragonList.forEach(this::add);
    }

    public void show() {
        printer.printCollection(dragonHashSet, OBJECT);
    }

    public void updateId(Integer id, Dragon dragon) {
        dragonHashSet.remove(idMap.get(id));
        dragon.setId(id);
        try {
            if (dragon.validated() != null) {
                dragonHashSet.add(dragon);
                idMap.put(id, dragon);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Вы ввели некорректные поля для объекта");
        }
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
        printer.println(String.format("%-30s", "Тип коллекции: ") + " " + collectionName, HELP);
        printer.println(String.format("%-30s", "Дата и время создания коллекции ") + " " + creationDate, HELP);
        printer.println(String.format("%-30s", "Размер коллекции: ") + " " + dragonHashSet.size(), HELP);
    }

    public void filterGreaterThanAge(Long age) {
        printer.println("Элементы коллекции, у которых поле age больше заданного: ", HELP);

        dragonHashSet
                .stream()
                .filter(d -> age.compareTo(d.getAge()) < 0)
                .forEach(d -> printer.println(d, OBJECT));
    }

    public void countGreaterThanKiller(Object personObj) {
        Person killer = (Person) personObj;
        long cnt = dragonHashSet
                .stream()
                .filter(d -> d.getKiller().compareTo(killer) > 0)
                .count();
        printer.println("Элементов коллекции, у которых поле killer больше заданного – " + cnt, HELP);
    }

    public void addIfMax(Dragon dragon) {
        if (dragon.compareTo(Collections.max(dragonHashSet)) > 0) {
            dragonHashSet.add(dragon);
        }
    }

    public static Integer mex() {
        if (idMap.isEmpty()) return 1;
        for (int i = 1; i <= idMap.lastKey() + 1; ++i) {
            if (!idMap.containsKey(i))
                return i;
        }
        return idMap.lastKey() + 1;
    }

    public boolean containsId(Integer id) {
        if (!idMap.containsKey(id)) throw new IllegalArgumentException("Вы ввели некорректный id.");
        return true;
    }
}

