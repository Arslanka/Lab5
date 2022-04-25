package io;


import data.Dragon;

import java.util.Collection;

import static io.ConsoleColor.*;

public class Printer {

    public void print(Collection<Dragon> dragonCollection) {
        for (Dragon d : dragonCollection) {
            System.out.println(d);
        }
    }

    public void printName() {
        System.out.print(PURPLE.wrapped("   Введите имя:"));
    }

    public void printDateTime(boolean isBirthday) {
        String birthdayWord = "";
        if (isBirthday)
            birthdayWord = CYAN.wrapped("РОЖДЕНИЯ");
        System.out.print(PURPLE.wrapped("   Введите дату и время ") + birthdayWord + PURPLE.wrapped( " в формате: yyyy-MM-dd HH:mm:ss a z:"));
    }

    public void printDescription(String objectName) {
        System.out.println(BLUE.wrapped("Введите данные для создания объекта " + objectName + ":"));
    }

    public void printCoordinate(char cName) {
        System.out.print(PURPLE.wrapped("   Введите координату " + cName + ':'));
    }

    public void printNationality() {
        System.out.print(PURPLE.wrapped("   Введите национальность:"));
    }

    public void printAge() {
        System.out.print(PURPLE.wrapped("   Введите возраст:"));
    }

    public void printWeight() {
        System.out.print(PURPLE.wrapped("   Введите вес:"));
    }

    public void printType() {
        System.out.print(PURPLE.wrapped("   Введите тип:"));
    }

    public void printColor() {
        System.out.print(PURPLE.wrapped("   Введите цвет:"));
    }

    public void printId() {
        System.out.print(PURPLE.wrapped("   Введите id:"));
    }

}
