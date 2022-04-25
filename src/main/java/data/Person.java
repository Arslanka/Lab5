package data;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    private Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть \"");
        }
        this.name = name;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) throws IllegalArgumentException {
        if (birthday == null) {
            throw new IllegalArgumentException("День рождения не может быть null");
        }
        this.birthday = birthday;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("Национальность не может быть null");
        }
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int compareTo(Person personObj) {
        return name.compareTo(personObj.getName());
    }

    public static class Builder {
        private final Person newPerson;

        public Builder() {
            newPerson = new Person();
        }

        public Builder withName(String name) throws IllegalArgumentException {
            newPerson.setName(name);
            return this;
        }

        public Builder withBirthday(java.time.ZonedDateTime birthday) throws IllegalArgumentException {
            newPerson.setBirthday(birthday);
            return this;
        }

        public Builder withNationality(Country nationality) throws IllegalArgumentException {
            newPerson.setNationality(nationality);
            return this;
        }

        public Builder withLocation(Location location) throws IllegalArgumentException {
            newPerson.setLocation(location);
            return this;
        }

        public Person build() throws IllegalArgumentException {
            if (newPerson.name == null || newPerson.birthday == null || newPerson.nationality == null || newPerson.location == null) {
                throw new IllegalArgumentException();
            }
            return newPerson;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday) && nationality == person.nationality && Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, nationality, location);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }
}
