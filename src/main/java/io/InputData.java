package io;

import data.Color;
import data.Country;
import data.DragonType;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;


public class InputData {
    public InputData() {
    }


    public String getName(String name) {
        try {
            return (name.equals("") ? null : name);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Вы ввели некорректное имя. Пожалуйста, введите имя еще раз");
        }
    }
    public String getFileName(String fileName) {
        try {
            return (fileName.equals("") ? null : fileName);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Вы ввели некорректное название файла. Пожалуйста, введите название еще раз");
        }
    }

    public ZonedDateTime getDateTime(String dateTime) {
        try {
            ZoneId timeZone = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);
            return zonedDateTime;
        } catch (DateTimeException e) {
            throw new IllegalStateException("Вы ввели некорректную дату. Пожалуйста, введите дату  еще раз");
        }
    }

    public Integer getIntCoordinate(String intCoordinate) {
        try {
            return Integer.parseInt(intCoordinate);
        } catch (InputMismatchException | NumberFormatException | IllegalStateException e) {
            throw new IllegalStateException("Вы ввели некорректную координату" + ". Пожалуйста, введите  координату еще раз");
        }
    }

    public Double getDoubleCoordinate(String doubleCoordinate) {
        try {
            return Double.parseDouble(doubleCoordinate);
        } catch (InputMismatchException | NumberFormatException | IllegalStateException e) {
            throw new IllegalStateException("Вы ввели некорректную координату с плавающей точкой" + ". Пожалуйста, введите координату еще раз");
        }
    }


    public Country getNationality(String nationality) {
        try {
            return Country.valueOf(nationality.toUpperCase());
        } catch (InputMismatchException | IllegalArgumentException e) {
            throw new IllegalStateException("Вы ввели некорректную национальность. Пожалуйста, введите национальность еще раз");
        }
    }

    public Color getColor(String color) {
        try {
            return Color.valueOf(color.toUpperCase());
        } catch (InputMismatchException | IllegalArgumentException e) {
            throw new IllegalStateException("Вы ввели некорректный цвет. Пожалуйста, введите цвет еще раз");
        }
    }

    public DragonType getType(String type) {
        try {
            return DragonType.valueOf(type.toUpperCase());
        } catch (InputMismatchException | IllegalArgumentException e) {
            throw new IllegalStateException("Вы ввели некорректный тип. Пожалуйста, введите тип еще раз");
        }
    }

    public Long getAge(String age) {
        try {
            return Long.parseLong(age);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new IllegalStateException("Вы ввели некорректный возраст. Пожалуйста, введите возраст еще раз");
        }
    }

    public Float getWeight(String weight) {
        try {
            return  Float.parseFloat(weight);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new IllegalStateException("Вы ввели некорректный вес. Пожалуйста, введите вес еще раз");
        }
    }

    public Integer getId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new IllegalStateException("Вы ввели некорректный id. Пожалуйста, введите id еще раз");
        }
    }
}
