package io.request;

import data.Dragon;
import io.InputData;
import io.Printer;
import data.*;

import static io.ConsoleColor.*;

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
        printer.println("Введите данные для создания объекта Dragon: ", OBJECT);
        requestElement.get("   Введите имя: ",
                inputData::getName,
                dragonBuilder::withName, true);
        dragonBuilder.withCoordinates(requestCoordinates.get().build());
        requestElement.get("   Введите возраст: ",
                inputData::getAge,
                dragonBuilder::withAge, true);
        requestElement.get("   Введите вес: ",
                inputData::getWeight,
                dragonBuilder::withWeight, true);
        requestElement.get(HELP.wrapped("   Возможные цвета: " + Color.nameList()) + FIELD.wrapped("\n   Введите цвет: "),
                inputData::getColor,
                dragonBuilder::withColor, true);
        requestElement.get(HELP.wrapped("   Возможные цвета: " + DragonType.nameList()) + FIELD.wrapped("\n   Введите тип: "),
                inputData::getType,
                dragonBuilder::withType, true);
        dragonBuilder.withPerson(requestPerson.get().build());
        return dragonBuilder;
    }
}
