package data;

import java.lang.IllegalArgumentException;
import java.util.Objects;

public class Location {
    private Integer x; //Поле не может быть null
    private Integer y;
    private Integer z; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 346, Поле не может быть null

    private Location() {
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public void setX(Integer x) throws IllegalArgumentException {
        if (x == null) {
            throw new IllegalArgumentException("Координата X не может быть null");
        }
        this.x = x;
    }

    public void setY(Integer y) {
        if (y == null) {
            throw new IllegalArgumentException("Координата Y не может быть null");
        }
        this.y = y;
    }

    public void setZ(Integer z) throws IllegalArgumentException {
        if (z == null) {
            throw new IllegalArgumentException("Координата Z не может быть null");
        }
        this.z = z;
    }

    public void setName(String name) throws IllegalArgumentException { //IncorrectInputException
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null"); //Обернуть эксепшны
        }

        if (name.length() > 346) {
            throw new IllegalArgumentException("Длина имени не может быть больше 346");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private final Location newLocation;

        public Builder() {
            newLocation = new Location();
        }

        public Builder withName(String name) throws IllegalArgumentException {
            newLocation.setName(name);
            return this;
        }

        public Builder withX(Integer x) throws IllegalArgumentException {
            newLocation.setX(x);
            return this;
        }

        public Builder withY(Integer y) throws IllegalArgumentException {
            newLocation.setY(y);
            return this;
        }

        public Builder withZ(Integer z) throws IllegalArgumentException {
            newLocation.setZ(z);
            return this;
        }

        public Location build()  {
            return newLocation;
        }
    }

    public Location validated() {
        setX(this.x);
        setY(this.y);
        setZ(this.z);
        setName(this.name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(y, location.y) && Objects.equals(x, location.x) && Objects.equals(z, location.z) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
