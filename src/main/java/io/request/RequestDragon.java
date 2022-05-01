package io.request;

import data.Coordinates;
import data.Dragon;
import io.InputData;
import io.Printer;
import data.*;

import static io.ConsoleColor.BLUE;
import static io.ConsoleColor.CYAN;

public class RequestDragon {
    private final RequestElement requestElement;
    private final Printer printer;
    private final InputData inputData;

    public RequestDragon(RequestElement requestElement, Printer printer, InputData inputData) {
        this.requestElement = requestElement;
        this.printer = printer;
        this.inputData = inputData;
    }

    public Dragon.Builder get() {
        Dragon.Builder dragonBuilder = new Dragon.Builder();
        RequestPerson requestPerson = new RequestPerson(requestElement, printer, inputData);
        RequestCoordinates requestCoordinates = new RequestCoordinates(requestElement, printer, inputData);
        printer.println("Введите данные для создания объекта Dragon: ", BLUE);
        requestElement.get("   Введите имя: ",
                inputData::getName,
                dragonBuilder::withName);
        dragonBuilder.withCoordinates(requestCoordinates.get().build());
        requestElement.get("   Введите возраст: ",
                inputData::getAge,
                dragonBuilder::withAge);
        requestElement.get("   Введите вес: ",
                inputData::getWeight,
                dragonBuilder::withWeight);
        printer.println("   Возможные цвета: " + Color.nameList(), CYAN);
        requestElement.get("   Введите цвет: ",
                inputData::getColor,
                dragonBuilder::withColor);
        printer.println("   Возможные типы: " + DragonType.nameList(), CYAN);
        requestElement.get("   Введите тип: ",
                inputData::getType,
                dragonBuilder::withType);
        dragonBuilder.withPerson(requestPerson.get().build());
        return dragonBuilder;
    }
}
