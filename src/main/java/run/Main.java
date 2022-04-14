package run;

import data.*;
import file.JsonFile;
import file.TextFile;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("dragonCollection.json");
        File outputFile = new File("Another.json");
        FileReader reader = new FileReader(outputFile);
        Dragon dragon = new Dragon.Builder()
                .withId()
                .withName("Rafail")
                .withCoordinates(new Coordinates.Builder()
                        .withX(1)
                        .withY(2.0)
                        .build())
                .withCreationDate()
                .withAge(49L)
                .withWeight((float) 85.5)
                .withColor(Color.GREEN)
                .withType(DragonType.FIRE)
                .withPerson(new Person.Builder()
                        .withName("Arslan")
                        .withBirthday(java.time.ZonedDateTime.now())
                        .withLocation(new Location.Builder().
                                withName("Kazan")
                                .withX(2)
                                .withY(1)
                                .withZ(10)
                                .build())
                        .withNationality(Country.GERMANY).
                        build())
                .build();
        JsonFile jsonFile = new JsonFile(new TextFile(outputFile));
        Set<Dragon> dragonSet = new HashSet<>();
        dragonSet.add(dragon);
        jsonFile.write(dragonSet);
    }
}