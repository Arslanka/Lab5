package io;

import data.Coordinates;
import data.Dragon;
import data.Location;
import data.Person;

import java.util.InputMismatchException;
import java.util.Scanner;

import static io.ConsoleColor.RED;

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
        printer.printDescription("Coordinates");
        getCoordinate(coordinatesBuilder, 'X', false);
        getCoordinate(coordinatesBuilder, 'Y', true);
        return coordinatesBuilder;
    }

    public Coordinates.Builder getCoordinate(Coordinates.Builder coordinatesBuilder, char cName, boolean isDouble) {
        while (true) {
            try {
                printer.printCoordinate(cName);
                if (!isDouble) {
                    Integer coordinate = inputData.getIntCoordinate(sc.nextLine().trim().toLowerCase(), cName);
                    return coordinatesBuilder.withX(coordinate);
                } else {
                    Double coordinate = inputData.getDoubleCoordinate(sc.nextLine().trim().toLowerCase(), cName);
                    return coordinatesBuilder.withY(coordinate);
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }


    public Person.Builder getPerson() {
        Person.Builder personBuilder = new Person.Builder();
        printer.printDescription("Person");
        getName(personBuilder);
        getBirthday(personBuilder);
        getNationality(personBuilder);
        getLocation(personBuilder);
        return personBuilder;
    }

    public Person.Builder getLocation(Person.Builder personBuilder) {
        while (true) {
            try {
                return personBuilder.withLocation(getLocation().build());
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Person.Builder getName(Person.Builder personBuilder) { //TODO Реализовать интерфейс BUILDER
        while (true) {
            try {
                printer.printName();
                return personBuilder.withName(inputData.getName(sc.nextLine().trim().toLowerCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Person.Builder getBirthday(Person.Builder personBuilder) { //TODO Реализовать интерфейс BUILDER
        while (true) {
            try {
                printer.printDateTime(true);
                return personBuilder.withBirthday(inputData.getDateTime(sc.nextLine().trim().toLowerCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Person.Builder getNationality(Person.Builder personBuilder) { //Реализовать интерфейс BUILDER
        while (true) {
            try {
                printer.printNationality();
                return personBuilder.withNationality(inputData.getNationality(sc.nextLine().trim().toUpperCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
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

    public Location.Builder getInt(Location.Builder locationBuilder, char cName) {
        while (true) {
            try {
                printer.printCoordinate(cName);
                if (cName == 'X') {
                    return locationBuilder.withX(inputData.getIntCoordinate(sc.nextLine().trim().toLowerCase(), cName));
                } else if (cName == 'Y') {
                    return locationBuilder.withY(inputData.getIntCoordinate(sc.nextLine().trim().toLowerCase(), cName));
                } else if (cName == 'Z')
                    return locationBuilder.withZ(inputData.getIntCoordinate(sc.nextLine().trim().toLowerCase(), cName));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Location.Builder getName(Location.Builder locationBuilder) {
        while (true) {
            try {
                printer.printName();
                return locationBuilder.withName(inputData.getName(sc.nextLine().trim().toLowerCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Dragon.Builder getDragon() {
        Dragon.Builder dragonBuilder = new Dragon.Builder();
        printer.printDescription("Dragon");
        getName(dragonBuilder);
        getCoordinates(dragonBuilder);
        getAge(dragonBuilder);
        getWeight(dragonBuilder);
        getColor(dragonBuilder);
        getType(dragonBuilder);
        getPerson(dragonBuilder);
        return dragonBuilder;
    }

    public Dragon.Builder getAge(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.printAge();
                return dragonBuilder.withAge(inputData.getAge(sc.nextLine().trim().toLowerCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Dragon.Builder getWeight(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.printWeight();
                return dragonBuilder.withWeight(inputData.getWeight(sc.nextLine().trim().toLowerCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Dragon.Builder getColor(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.printColor();
                return dragonBuilder.withColor(inputData.getColor(sc.nextLine().trim().toUpperCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Dragon.Builder getType(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.printType();
                return dragonBuilder.withType(inputData.getType(sc.nextLine().trim().toUpperCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Dragon.Builder getName(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                printer.printName();
                return dragonBuilder.withName(inputData.getName(sc.nextLine().trim().toLowerCase()));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(RED.wrapped(e.getMessage()));
            }
        }
    }

    public Dragon.Builder getCoordinates(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                return dragonBuilder.withCoordinates(getCoordinates().build());
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Dragon.Builder getPerson(Dragon.Builder dragonBuilder) {
        while (true) {
            try {
                return dragonBuilder.withPerson(getPerson().build());
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Integer getId() {
        while (true) {
            try {
                printer.printId();
                return inputData.getId(sc.nextLine().trim().toLowerCase());
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Float getWeight() {
        while (true) {
            try {
                printer.printWeight();
                return inputData.getWeight(sc.nextLine().trim().toLowerCase());
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Long getAge() {
        while (true) {
            try {
                printer.printAge();
                return inputData.getAge(sc.nextLine().trim().toLowerCase());
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
