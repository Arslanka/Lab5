package io;

import data.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static io.ConsoleColor.*;

public class JsonString {
    private final InputData inputData;
    private final Scanner sc;
    private final Printer printer;

    public JsonString(InputData inputData, Scanner sc, Printer printer) {
        this.inputData = inputData;
        this.sc = sc;
        this.printer = printer;
    }

    public Coordinates.Builder getCoordinates() {
        Coordinates.Builder coordinatesBuilder = new Coordinates.Builder();
        printer.println("Введите данные для создания объекта Coordinates :", BLUE); //??
        getCoordinate(coordinatesBuilder, 'X', false);
        getCoordinate(coordinatesBuilder, 'Y', true);
        return coordinatesBuilder;
    }

    public void getCoordinate(Coordinates.Builder coordinatesBuilder, char cName, boolean isDouble) {
        while (true) {
            try {
                printer.print("   Введите координату " + cName + ':', PURPLE);
                if (!isDouble) {
                    Integer coordinate = inputData.getIntCoordinate(sc.nextLine().trim(), cName);
                    coordinatesBuilder.withX(coordinate);
                } else {
                    Double coordinate = inputData.getDoubleCoordinate(sc.nextLine().trim(), cName);
                    coordinatesBuilder.withY(coordinate);
                }
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }


    public Person.Builder getPerson() {
        Person.Builder personBuilder = new Person.Builder();
        printer.println("Введите данные для создания объекта " + "Person" + ":", BLUE);
        getName(personBuilder);
        getBirthday(personBuilder);
        getNationality(personBuilder);
        getLocation(personBuilder);
        return personBuilder;
    }

    public void getLocation(Person.Builder personBuilder) {
        while (true) {
            try {
                personBuilder.withLocation(getLocation().build());
                return;
            } catch (IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Person.Builder getName(Person.Builder personBuilder) { //TODO Реализовать интерфейс BUILDER
        while (true) {
            try {
                printer.print("   Введите имя:", PURPLE);
                return personBuilder.withName(inputData.getName(sc.nextLine().trim()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public void getBirthday(Person.Builder personBuilder) { //TODO Реализовать интерфейс BUILDER
        while (true) {
            try {
                printer.print("   Введите дату и время рождения  в формате: yyyy-MM-dd HH:mm:ss a z:", PURPLE);
                personBuilder.withBirthday(inputData.getDateTime(sc.nextLine().trim()));
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public void getNationality(Person.Builder personBuilder) { //Реализовать интерфейс BUILDER
        while (true) {
            try {
                printer.println("   Возможные национальности: " + Country.nameList(), CYAN);
                printer.print("   Введите национальность:", PURPLE);
                personBuilder.withNationality(inputData.getNationality(sc.nextLine().trim().toUpperCase()));
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Location.Builder getLocation() { //Реализовать интерфейс BUILDER
        Location.Builder locationBuilder = new Location.Builder();
        getInt(locationBuilder, 'X');
        getInt(locationBuilder, 'Y');
        getInt(locationBuilder, 'Z');
        getName(locationBuilder);
        return locationBuilder;
    }

    public void getInt(Location.Builder locationBuilder, char cName) {
        while (true) {
            try {
                printer.print("   Введите координату " + cName + ':', PURPLE);
                if (cName == 'X') {
                    locationBuilder.withX(inputData.getIntCoordinate(sc.nextLine().trim(), cName));
                    return;
                } else if (cName == 'Y') {
                    locationBuilder.withY(inputData.getIntCoordinate(sc.nextLine().trim(), cName));
                    return;
                } else if (cName == 'Z') {
                    locationBuilder.withZ(inputData.getIntCoordinate(sc.nextLine().trim(), cName));
                    return;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Location.Builder getName(Location.Builder locationBuilder) {
        while (true) {
            try {
                printer.print("   Введите имя:", PURPLE);
                return locationBuilder.withName(inputData.getName(sc.nextLine().trim()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Dragon.Builder getDragon() {
        Dragon.Builder dragonBuilder = new Dragon.Builder();
        printer.println("Введите данные для создания объекта Dragon:", BLUE);
        getName(dragonBuilder);
        getCoordinates(dragonBuilder);
        getAge(dragonBuilder);
        getWeight(dragonBuilder);
        getColor(dragonBuilder);
        getType(dragonBuilder);
        getPerson(dragonBuilder);
        return dragonBuilder;
    }

    public void getAge(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.print("   Введите возраст:", PURPLE);
                dragonBuilder.withAge(inputData.getAge(sc.nextLine().trim()));
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public void getWeight(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.print("   Введите вес:", PURPLE);
                dragonBuilder.withWeight(inputData.getWeight(sc.nextLine().trim()));
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public void getColor(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.println("   Возможные цвета: " + Color.nameList(), CYAN);
                printer.print("   Введите цвет:", PURPLE);
                dragonBuilder.withColor(inputData.getColor(sc.nextLine().trim().toUpperCase()));
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public void getType(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.println("   Возможные типы: " + DragonType.nameList(), CYAN);
                printer.print("   Введите тип:", PURPLE);
                dragonBuilder.withType(inputData.getType(sc.nextLine().trim().toUpperCase()));
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Dragon.Builder getName(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.print("   Введите имя:", PURPLE);
                return dragonBuilder.withName(inputData.getName(sc.nextLine().trim()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public void getCoordinates(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                dragonBuilder.withCoordinates(getCoordinates().build());
                return;
            } catch (IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public void getPerson(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                dragonBuilder.withPerson(getPerson().build());
                return;
            } catch (IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Integer getId() {
        while (true) {
            try {
                printer.print("   Введите id:", PURPLE);
                return inputData.getId(sc.nextLine().trim().toLowerCase());
            } catch (IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Float getWeight() {
        while (true) {
            try {
                printer.print("   Введите вес:", PURPLE);
                return inputData.getWeight(sc.nextLine().trim());
            } catch (IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public Long getAge() {
        while (true) {
            try {
                printer.print("   Введите возраст:", PURPLE);
                return inputData.getAge(sc.nextLine().trim());
            } catch (IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }
}
