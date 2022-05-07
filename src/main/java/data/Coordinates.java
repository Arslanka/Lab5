package data;

import java.util.Objects;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private Double y; //Максимальное значение поля: 508, Поле не может быть null

    private Coordinates() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) throws IllegalArgumentException {
        if (x == null) {
            throw new IllegalArgumentException("Координата X не может быть null");
        }
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        if (y == null) {
            throw new IllegalArgumentException("Координата Y не может быть null");
        }
        if (y > 508)
            throw new IllegalArgumentException("Координата Y не может быть больше 508");
        this.y = y;
    }


    public static class Builder {
        private final Coordinates newCoordinates;

        public Builder() {
            newCoordinates = new Coordinates();
        }

        public Builder withX(Integer x) throws IllegalArgumentException {
            newCoordinates.setX(x);
            return this;
        }

        public Builder withY(Double y) throws IllegalArgumentException {
            newCoordinates.setY(y);
            return this;
        }

        public Coordinates build() {
            return newCoordinates;
        }
    }

    public Coordinates validated() {
        setX(this.x);
        setY(this.y);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String
    toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
