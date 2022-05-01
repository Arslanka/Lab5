package io;

import collection.CollectionManager;
import com.google.gson.JsonParseException;
import commands.*;
import exceptions.FileReadPermissionException;
import file.JsonFile;
import file.TextFile;
import io.request.RequestDragon;
import io.request.RequestElement;
import io.request.RequestPerson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Supplier;

import static io.ConsoleColor.*;

public class Console {
    private final Scanner sc;
    private final Map<String, Command> commandsByName = new HashMap<>();
    private final Map<String, Supplier<Object[]>> supplierMap = new HashMap<>();
    private final CollectionManager collectionManager;
    private final Printer printer;

    public Console(Scanner sc, CollectionManager collectionManager, Printer printer) {
        this.sc = sc;
        this.collectionManager = collectionManager;
        this.printer = printer;
        fillCommands();
    }

    public void startInteractiveMode() {
        try {
            printer.println("Введите название файла, данными из которого будет заполнена коллекция", YELLOW);
            String fileName = sc.nextLine();
            final File file = new File(fileName);
            final TextFile textFile = new TextFile(file);
            final JsonFile jsonFile = new JsonFile(textFile);
            try {
                collectionManager.add(jsonFile.read());
            } catch (NoSuchElementException e) {
                printer.println(e.getMessage(), RED);
                return;
            }
            ExCommand exCommand = new ExCommand(this.commandsByName, supplierMap, sc, this.printer);
            boolean needExit = false;
            while (!needExit) {
                printer.println(("Введите команду:"), GREEN);
                try {
                    needExit = exCommand.run(sc.nextLine().trim().toLowerCase());
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException("Вы ничего не ввели. Пожалуйста введите данные.");
                }
            }
            printer.println("Исполнение программы остановлено", YELLOW);
        } catch (IllegalArgumentException | FileReadPermissionException | IOException | JsonParseException e) {
            printer.println((e.getMessage()), RED);
            startInteractiveMode();
        } catch (NoSuchElementException e) {
            printer.println("Программа завершилась некорректно. Пожалуйста, перезапустите программу.", RED);
        }
    }

    private void fillCommands() {
        RequestElement requestElement = new RequestElement(sc, printer);
        InputData inputData = new InputData();
        commandsByName.put("help", new HelpCommand(new Printer()));
        supplierMap.put("help",
                () -> new Object[]{commandsByName});
        commandsByName.put("info",
                new InfoCommand(collectionManager));
        supplierMap.put("info",
                () -> new Object[]{});
        commandsByName.put("show", new ShowCommand(collectionManager));
        supplierMap.put("show",
                () -> new Object[]{});
        commandsByName.put("add", new AddCommand(collectionManager));
        supplierMap.put("add",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("update", new UpdateIdCommand(collectionManager));
        supplierMap.put("update",
                () -> new Object[]{requestElement.get("Введите id:", inputData::getId),
                        new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        supplierMap.put("remove_by_id",
                () -> new Object[]{requestElement.get("Введите id:", inputData::getId)});
        commandsByName.put("clear", new ClearCommand(collectionManager));
        supplierMap.put("clear",
                () -> new Object[]{});
        commandsByName.put("save", new SaveCommand(collectionManager));
        supplierMap.put("save",
                () -> {
                    try {
                        System.out.print(BLUE.wrapped("Введите название файла:")); //todo fix
                        return new Object[]{new JsonFile(new TextFile(new File(sc.nextLine().trim())))};
                    } catch (FileNotFoundException e) {
                        printer.println(e.getMessage(), RED);
                    }
                    throw new IllegalArgumentException("Вы ввели некорректные данные");//TODO FIX
                });
        commandsByName.put("execute_script", new ExecuteScriptCommand());
        //supplierMap.put("execute_script", () -> new Object[]{jsonString.getDragon().build()});
        commandsByName.put("add_if_max", new AddIfMaxCommand(collectionManager));
        supplierMap.put("add_if_max",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        supplierMap.put("add",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_lower", new RemoveLowerCommand(collectionManager));
        supplierMap.put("remove_lower",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_all_by_weight", new RemoveAllByWeight(collectionManager));
        supplierMap.put("remove_all_by_weight",
                () -> new Object[]{requestElement.get("Введите вес:", inputData::getWeight)});
        commandsByName.put("count_greater_than_killer", new CountGreaterThanKillerCommand(collectionManager));
        supplierMap.put("count_greater_than_killer",
                () -> new Object[]{new RequestPerson(requestElement, printer, inputData).get().build()});
        commandsByName.put("filter_greater_than_age", new FilterGreaterThanAgeCommand(collectionManager));
        supplierMap.put("filter_greater_than_age",
                () -> new Object[]{requestElement.get("Введите возраст:", inputData::getAge)});
    }

}