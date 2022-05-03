package data;

import collection.Collection;

import java.time.ZonedDateTime;
import java.util.Objects;


public class Dragon implements Comparable<Dragon> {

    private Integer id = Collection.mex();
    ;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0
    private Float weight; //Значение поля должно быть больше 0
    private Color color; //Поле не может быть null
    private DragonType type; //Поле может быть null
    private Person killer; //Поле может быть null

    private Dragon() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть \"\"");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws IllegalArgumentException {
        if (coordinates == null) throw new IllegalArgumentException("Координаты не могут быть null");
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        creationDate = ZonedDateTime.now();
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) throws IllegalArgumentException {
        if (age == null) throw new IllegalArgumentException("Возраст не может быть null");
        if (age <= 0) throw new IllegalArgumentException("Возраст не может быть меньше или равен 0");
        this.age = age;
    }

    public void setId(Integer id) {
        if (id <= 0) throw new IllegalArgumentException("Значение id должно быть положительным");
        if (id >= Integer.MAX_VALUE)
            throw new IllegalArgumentException("Значение id должно быть меньше чем " + Integer.MAX_VALUE);
        this.id = id;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) throws IllegalArgumentException {
        if (weight == null) throw new IllegalArgumentException("Вec не может быть null");
        if (weight <= 0) throw new IllegalArgumentException("Вec не может быть меньше или равен 0");
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) throws IllegalArgumentException {
        if (color == null) throw new IllegalArgumentException("Цвет не может быть null");
        this.color = color;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        if (type == null) throw new IllegalArgumentException("Тип не может быть null");
        this.type = type;
    }

    public Person getKiller() {
        return killer;
    }

    public void setKiller(Person killer) {
        if (killer == null) throw new IllegalArgumentException("Киллер не может быть null");
        this.killer = killer;
    }

    @Override
    public int compareTo(Dragon dragonObj) {
        return age.compareTo(dragonObj.getAge());
    }

    public static class Builder {
        private final Dragon newDragon;

        public Builder() {
            newDragon = new Dragon();
        }


        public Builder withName(String name) throws IllegalArgumentException {
            newDragon.setName(name);
            return this;
        }

        public Builder withCoordinates(Coordinates coordinates) throws IllegalArgumentException {
            newDragon.setCoordinates(coordinates);
            return this;
        }

        public Builder withCreationDate() {
            newDragon.setCreationDate();
            return this;
        }

        public Builder withAge(Long age) throws IllegalArgumentException {
            newDragon.setAge(age);
            return this;
        }

        public Builder withWeight(Float weight) throws IllegalArgumentException {
            newDragon.setWeight(weight);
            return this;
        }

        public Builder withColor(Color color) throws IllegalArgumentException {
            newDragon.setColor(color);
            return this;
        }

        public Builder withType(DragonType dragonType) throws IllegalArgumentException {
            newDragon.setType(dragonType);
            return this;
        }

        public Builder withPerson(Person killer) throws IllegalArgumentException {
            newDragon.setKiller(killer);
            return this;
        }


        public Dragon build() throws IllegalArgumentException {
//            if (newDragon.name == null || newDragon.creationDate == null || newDragon.coordinates == null ||
//                    newDragon.id == null || newDragon.age == null
//                    || newDragon.color == null || newDragon.weight == null || newDragon.killer.getName() == null) {
//                throw new IllegalArgumentException("Вы ввели некорректные данные");
//            }
            return newDragon;
        }

    }

    public Dragon isValid() {
        setName(this.name);
        setId(this.id);
        setCoordinates(this.coordinates.isValid());
        setAge(this.age);
        setWeight(this.weight);
        setColor(this.color);
        setType(this.type);
        setKiller(this.killer.isValid());
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dragon dragon = (Dragon) o;
        return Objects.equals(id, dragon.id) && Objects.equals(name, dragon.name) && Objects.equals(coordinates, dragon.coordinates) && Objects.equals(creationDate, dragon.creationDate) && Objects.equals(age, dragon.age) && Objects.equals(weight, dragon.weight) && color == dragon.color && type == dragon.type && Objects.equals(killer, dragon.killer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, weight, color, type, killer);
    }

    @Override
    public String toString() { //TODO DO PERFECT
        return "Dragon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", weight=" + weight +
                ", color=" + color +
                ", type=" + type +
                ", killer=" + killer +
                '}';
    }
}
