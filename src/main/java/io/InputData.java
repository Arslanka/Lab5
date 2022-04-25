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
import java.util.Scanner;

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

    public ZonedDateTime getDateTime(String dateTime) {
        try {
            ZoneId timeZone = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);
            return (dateTime.equals("") ? null : zonedDateTime);
        } catch (DateTimeException e) {
            throw new IllegalStateException("Вы ввели некорректную дату. Пожалуйста, введите дату  еще раз");
        }
    }

    public Integer getIntCoordinate(String intCoordinate, char cName) {
        try {
            return (intCoordinate.equals("") ? null : Integer.parseInt(intCoordinate));
        } catch (InputMismatchException | NumberFormatException | IllegalStateException e) {
            throw new IllegalStateException("Вы ввели некорректную координату " + cName + ". Пожалуйста, введите  координату еще раз");
        }
    }

    public Double getDoubleCoordinate(String doubleCoordinate, char cName) {
        try {
            return (doubleCoordinate.equals("") ? null : Double.parseDouble(doubleCoordinate));
        } catch (InputMismatchException | NumberFormatException | IllegalStateException e) {
            throw new IllegalStateException("Вы ввели некорректную координату с плавающей точкой " + cName + ". Пожалуйста, введите координату еще раз");
        }
    }


    public Country getNationality(String nationality) {
        try {
            return Country.valueOf(nationality);
        } catch (InputMismatchException | IllegalArgumentException e) {
            throw new IllegalStateException("Вы ввели некорректную национальность. Пожалуйста, введите национальность еще раз");
        }
    }

    public Color getColor(String color) {
        try {
            return Color.valueOf(color);
        } catch (InputMismatchException | IllegalArgumentException e) {
            throw new IllegalStateException("Вы ввели некорректный цвет. Пожалуйста, введите цвет еще раз");
        }
    }

    public DragonType getType(String type) {
        try {
            return DragonType.valueOf(type);
        } catch (InputMismatchException | IllegalArgumentException e) {
            throw new IllegalStateException("Вы ввели некорректный тип. Пожалуйста, введите тип еще раз");
        }
    }

    public Long getAge(String age) {
        try {
            return (age.equals("") ? null : Long.parseLong(age));
        } catch (InputMismatchException e) {
            throw new IllegalStateException("Вы ввели некорректный тип. Пожалуйста, введите тип еще раз");
        }
    }

    public Float getWeight(String weight) {
        try {
            return (weight.equals("") ? null : Float.parseFloat(weight));
        } catch (InputMismatchException e) {
            throw new IllegalStateException("Вы ввели некорректный вес. Пожалуйста, введите вес еще раз");
        }
    }

    public Integer getId(String id) {
        try {
            return (id.equals("") ? null : Integer.parseInt(id));
        } catch (InputMismatchException e) {
            throw new IllegalStateException("Вы ввели некорректный id. Пожалуйста, введите id еще раз");
        }
    }
}
