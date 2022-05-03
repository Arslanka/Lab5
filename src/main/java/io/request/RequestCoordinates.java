package io.request;

import data.Coordinates;
import io.InputData;
import io.Printer;

import static io.ConsoleColor.BLUE;

public class RequestCoordinates {
    private final RequestElement requestElement;
    private final Printer printer;
    private final InputData inputData;

    public RequestCoordinates(RequestElement requestElement, Printer printer, InputData inputData) {
        this.requestElement = requestElement;
        this.printer = printer;
        this.inputData = inputData;
    }

    public Coordinates.Builder get() {
        Coordinates.Builder coordinatesBuilder = new Coordinates.Builder();
        printer.println("Введите данные для создания объекта Coordinates: ", BLUE);
        requestElement.get("   Введите координату X: ",
                inputData::getIntCoordinate,
                coordinatesBuilder::withX, true);
        requestElement.get("   Введите координату Y: ",
                inputData::getDoubleCoordinate,
                coordinatesBuilder::withY, true);
        return coordinatesBuilder;
    }
}
