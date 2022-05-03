package io.request;

import data.Location;
import data.Person;
import io.InputData;
import io.Printer;

import java.time.LocalDateTime;

import static io.ConsoleColor.BLUE;

public class RequestLocation {
    private final RequestElement requestElement;
    private final Printer printer;
    private final InputData inputData;

    public RequestLocation(RequestElement requestElement, Printer printer, InputData inputData) {
        this.requestElement = requestElement;
        this.printer = printer;
        this.inputData = inputData;
    }

    public Location.Builder get() {
        Location.Builder locationBuilder = new Location.Builder();
        printer.println("Введите данные для создания объекта Location: ", BLUE);
        requestElement.get("   Введите координату X: ",
                inputData::getIntCoordinate,
                locationBuilder::withX, true);
        requestElement.get("   Введите координату Y: ",
                inputData::getIntCoordinate,
                locationBuilder::withY, true);
        requestElement.get("   Введите координату Z: ",
                inputData::getIntCoordinate,
                locationBuilder::withZ, true);
        requestElement.get("   Введите имя: ",
                inputData::getName,
                locationBuilder::withName, true);
        return locationBuilder;
    }
}
