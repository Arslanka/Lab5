package io.request;

import data.Coordinates;
import data.Location;
import data.Person;
import io.InputData;
import io.Printer;

import static io.ConsoleColor.BLUE;

public class RequestPerson {
    private final RequestElement requestElement;
    private final Printer printer;
    private final InputData inputData;

    public RequestPerson(RequestElement requestElement, Printer printer, InputData inputData) {
        this.requestElement = requestElement;
        this.printer = printer;
        this.inputData = inputData;
    }

    public Person.Builder get() {
        Person.Builder personBuilder = new Person.Builder();
        RequestLocation requestLocation = new RequestLocation(requestElement, printer, inputData);
        printer.println("Введите данные для создания объекта " + "Person" + ":", BLUE);
        requestElement.get("   Введите имя: ",
                inputData::getName,
                personBuilder::withName);
        requestElement.get("   Введите день рождения: ",
                inputData::getDateTime,
                personBuilder::withBirthday);
        requestElement.get("   Введите национальность: ",
                inputData::getNationality,
                personBuilder::withNationality);
        personBuilder.withLocation(requestLocation.get().build());
        return personBuilder;
    }
}
