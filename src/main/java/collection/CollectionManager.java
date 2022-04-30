package collection;

import data.Dragon;
import data.Person;
import file.JsonFile;
import io.JsonString;
import io.Printer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static io.ConsoleColor.*;

public class CollectionManager {
    private final HashSet<Dragon> dragonHashSet = new HashSet<>();
    private final Printer printer = new Printer();
    public static final TreeMap<Object, Dragon> idMap = new TreeMap<>();
    private final LocalDateTime creationDate = LocalDateTime.now();

    public CollectionManager() {
    }

    public void add(Dragon dragon) {
        Object dragonId = dragon.getId();
        if (idMap.containsKey(dragonId))
            throw new NoSuchElementException("В коллекции уже есть объект с id " + dragonId + ". Пожалуйста, введите другое id");
        idMap.put(dragonId, dragon);
        dragonHashSet.add(dragon);
    }

    public void add(Collection<Dragon> dragonList) {
        dragonList.forEach(this::add);
    }

    public void show() {
        printer.printCollection(dragonHashSet, BLUE);
    }

    public void updateId(Object id, Object obj) {
        if (!idMap.containsKey(id)) throw new IllegalArgumentException("Вы ввели некорректный id.");
        dragonHashSet.remove(idMap.get(id));
        JsonString jsonString = (JsonString) obj;
        Dragon curDragon = jsonString.getDragon().build();
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
        printer.println("Коллекция успешно очищена", CYAN);
    }

    public void save(JsonFile jsonFile) throws IOException {
        jsonFile.write(dragonHashSet);
        printer.println("Коллекция сохранена в файл " + jsonFile, CYAN);
    }

    public void removeLower(Object dragonObj) {
        Dragon dragon = (Dragon) dragonObj;
        dragonHashSet.removeIf(d -> d.compareTo(dragon) < 0);
        idMap
             .keySet()
             .removeIf(id -> idMap.get(id).compareTo(dragon) < 0);
    }

    public void removeGreater(Object dragonObj) {
        Dragon dragon = (Dragon) dragonObj;
        dragonHashSet.removeIf(d -> d.compareTo(dragon) > 0);
        idMap
             .keySet()
             .removeIf(id -> idMap.get(id).compareTo(dragon) > 0);
    }

    public void removeByWeight(Object weightObj) {
        Float weight = (Float) weightObj;
        dragonHashSet.removeIf(d -> (Objects.equals(d.getWeight(), weight)));
        idMap
             .keySet()
             .removeIf(id -> Objects.equals(idMap.get(id).getWeight(), weight));
    }


    public void info() {
        String collectionName = "HashSet<Dragon> dragonHashSet";
        printer.println(collectionName, CYAN);
        printer.println(collectionName, CYAN);
        printer.println("Тип коллекции: " + collectionName, CYAN);
        printer.println("Дата и время создания коллекции " + creationDate, CYAN);
        printer.println("Размер коллекции: " + dragonHashSet.size(), CYAN);
    }

    public void filterGreaterThanAge(Object ageObj) {
        printer.print("Элементы коллекции, у которых поле age больше заданного – ", CYAN); //TODO stream API
        Long age = (Long) ageObj;
        dragonHashSet
                .stream()
                .filter(d -> d.getAge() > age)
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

    public void addIfMax(Object dragonObj) {
        Dragon dragon = (Dragon) dragonObj;
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

