package io.request;

import data.*;
import io.InputData;
import io.Printer;

import static io.ConsoleColor.*;

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
                personBuilder::withName, true);
        requestElement.get("   Введите день рождения в формате yyyy-MM-dd HH:mm:ss a z: ",
                inputData::getDateTime,
                personBuilder::withBirthday, true);
        requestElement.get(CYAN.wrapped("   Возможные цвета: " + Country.nameList()) + PURPLE.wrapped("\n   Введите национальность: "),
                inputData::getNationality,
                personBuilder::withNationality, true);
        personBuilder.withLocation(requestLocation.get().build());
        return personBuilder;
    }
}
