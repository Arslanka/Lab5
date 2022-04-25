package collection;

import data.Dragon;
import data.Person;
import file.JsonFile;
import io.JsonString;
import io.Printer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private final HashSet<Dragon> dragonHashSet = new HashSet<>();
    private final Printer printer = new Printer();
    public static final TreeMap idMap = new TreeMap();//TODO GENERICS
    private final LocalDateTime creationDate = LocalDateTime.now();

    public CollectionManager() {
    }

    public boolean add(Dragon dragon) {
        Object dragonId = dragon.getId();
        if (idMap.containsKey(dragonId))
            throw new NoSuchElementException("В коллекции уже есть объект с id " + dragonId + ". Пожалуйста, введите другое id");
        else {
            idMap.put(dragonId, dragon);
            return dragonHashSet.add(dragon);
        }
    }

    public void add(Collection<Dragon> dragonList) {
        for (Dragon dragon : dragonList) {
            add(dragon);
        }
    }

    public void show() {
        printer.print(dragonHashSet);
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
        dragonHashSet.remove((Dragon) idMap.get(id));
        idMap.remove(id);
    }

    public void clear() {
        dragonHashSet.clear();
    }

    public void save(JsonFile jsonFile) throws IOException {
        jsonFile.write(dragonHashSet);
    }

    public void removeLower(Object dragonObj) {
        Dragon dragon = (Dragon) dragonObj;
        dragonHashSet.removeIf(d -> d.compareTo(dragon) < 0);
        for (Object id : idMap.keySet()) {
            Dragon curDragon = (Dragon) idMap.get(id);
            if (curDragon.compareTo(dragon) < 0) idMap.remove(id);
        }
    }

    public void removeGreater(Object dragonObj) {
        Dragon dragon = (Dragon) dragonObj;
        dragonHashSet.removeIf(d -> d.compareTo(dragon) > 0);
        for (Object id : idMap.keySet()) {
            Dragon curDragon = (Dragon) idMap.get(id);
            if (curDragon.compareTo(dragon) > 0) idMap.remove(id);
        }
    }

    public void removeByWeight(Object weightObj) {
        Float weight = (Float) weightObj;
        dragonHashSet.removeIf(d -> (Objects.equals(d.getWeight(), weight)));
        for (Object id : idMap.keySet()) {
            Dragon curDragon = (Dragon) idMap.get(id);
            if (Objects.equals(curDragon.getWeight(), weight)) idMap.remove(id);
        }
    }


    public void info() {
        String collectionName = "HashSet<Dragon> dragonHashSet";
        System.out.println("Тип коллекции:" + collectionName);
        System.out.println("Дата и время создания коллекции" + creationDate);
        System.out.println("Размер коллекции:" + dragonHashSet.size());
    }

    public void filterGreaterThanAge(Object ageObj) {
        System.out.println("Элементы коллекции, у которых поле age больше заданного – ");
        Long age = (Long) ageObj;
        for (Dragon d : dragonHashSet) {
            if (d.getAge() > age)
                System.out.println(d);
        }
    }

    public void countGreaterThanKiller(Object personObj) {
        Person killer = (Person) personObj;
        int cnt = 0;
        for (Dragon d : dragonHashSet) {
            if (d.getKiller().compareTo(killer) > 0)
                ++cnt;
        }
        System.out.println("Элементов коллекции, у которых поле killer больше заданного – " + cnt);
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

