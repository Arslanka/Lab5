package io;

import collection.Collection;
import com.google.gson.JsonParseException;
import commands.*;
import exceptions.FileReadPermissionException;
import execute.CommandInterpreter;
import execute.AdvancedScript;
import execute.Script;
import file.FileManager;
import file.JsonFile;
import file.TextFile;
import io.request.RequestDragon;
import io.request.RequestElement;
import io.request.RequestPerson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Supplier;

import static io.ConsoleColor.*;

public class Console {
    public static final String SEPARATOR = "-----------------------";

    private Scanner sc;
    private final Map<String, Command> commandsByName = new HashMap<>();
    private final Map<String, Supplier<Object[]>> supplierMap = new HashMap<>();
    private final Collection collection;
    private final Printer printer;
    private boolean running = true;

    public Console(Scanner sc, Collection collection, Printer printer) {
        this.sc = sc;
        this.collection = collection;
        this.printer = printer;
        fillCommands(sc);
    }

    public void startInteractiveMode() {
        try {

            printer.println("Введите название файла, данными из которого будет заполнена коллекция", CONSOLE);
            String fileName = sc.nextLine();
            final File file = new File(fileName.trim());
            final TextFile textFile = new TextFile(file);
            final JsonFile jsonFile = new JsonFile(textFile);
            collection.add(jsonFile.read());
            CommandInterpreter exCommand = new CommandInterpreter(this.commandsByName, supplierMap, this.printer);
            running = true;
            while (running) {
                printer.println(("Введите команду:"), REQUEST);
                try {
                    running = exCommand.run(sc.nextLine().trim().toLowerCase());
                } catch (NoSuchElementException e) {
                    running = false;
                } catch (IllegalArgumentException | JsonParseException e) {
                    printer.println(e.getMessage(), ERROR);
                }
            }
            if (Script.getExecutableScriptsAmount() == 0)
                printer.println("Исполнение программы остановлено", CONSOLE);
        } catch (IllegalArgumentException | FileReadPermissionException | IOException | JsonParseException e) {
            printer.println(e.getMessage(), ERROR);
            startInteractiveMode();
        } catch (NoSuchElementException e) {
            printer.println("Программа завершилась некорректно. Пожалуйста, перезапустите программу.", ERROR);
        }
    }

    public void fillCommands(Scanner sc) {
        RequestElement requestElement = new RequestElement(sc, printer);
        InputData inputData = new InputData();
        commandsByName.put("help", new HelpCommand(new Printer()));
        supplierMap.put("help",
                () -> new Object[]{commandsByName});
        commandsByName.put("exit", new ExitCommand(new Printer()));
        supplierMap.put("exit",
                () -> new Object[]{});
        commandsByName.put("info",
                new InfoCommand(collection, printer));
        supplierMap.put("info",
                () -> new Object[]{});
        commandsByName.put("show", new ShowCommand(collection, printer));
        supplierMap.put("show",
                () -> new Object[]{});
        commandsByName.put("add", new AddCommand(collection, printer));
        supplierMap.put("add",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("update", new UpdateIdCommand(collection, printer));
        supplierMap.put("update",
                () -> new Object[]{collection.containsId(requestElement.get("Введите id:", inputData::getId, true)),
                        new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_by_id", new RemoveByIdCommand(collection, printer));
        supplierMap.put("remove_by_id",
                () -> new Object[]{requestElement.get("Введите id:", inputData::getId, true)});
        commandsByName.put("clear", new ClearCommand(collection, printer));
        supplierMap.put("clear",
                () -> new Object[]{});
        commandsByName.put("save", new SaveCommand(collection, printer));

        supplierMap.put("save",
                () -> new Object[]{new FileManager(requestElement.get("Введите название файла:", inputData::getFileName, false), printer).getJsonFileByName()});
        commandsByName.put("execute_advanced_script", new ExecuteAdvancedScriptCommand(printer));
        supplierMap.put("execute_advanced_script",
                () -> new Object[]{new AdvancedScript(new FileManager(requestElement.get("Введите название файла со скриптом:", inputData::getFileName, false), printer).getTextFileByName(), commandsByName, supplierMap, printer)});
        commandsByName.put("add_if_max", new AddIfMaxCommand(collection, printer));
        supplierMap.put("add_if_max",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_greater", new RemoveGreaterCommand(collection, printer));
        supplierMap.put("remove_greater",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_lower", new RemoveLowerCommand(collection, printer));
        supplierMap.put("remove_lower",
                () -> new Object[]{new RequestDragon(requestElement, printer, inputData).get().build()});
        commandsByName.put("remove_all_by_weight", new RemoveAllByWeight(collection, printer));
        supplierMap.put("remove_all_by_weight",
                () -> new Object[]{requestElement.get("Введите вес:", inputData::getWeight, true)});
        commandsByName.put("count_greater_than_killer", new CountGreaterThanKillerCommand(collection, printer));
        supplierMap.put("count_greater_than_killer",
                () -> new Object[]{new RequestPerson(requestElement, printer, inputData).get().build()});
        commandsByName.put("filter_greater_than_age", new FilterGreaterThanAgeCommand(collection, printer));
        supplierMap.put("filter_greater_than_age",
                () -> new Object[]{requestElement.get("Введите возраст:", inputData::getAge, true)});
        commandsByName.put("execute_script", new ExecuteScriptCommand(printer));
        supplierMap.put("execute_script",
                () -> new Object[]{new Script(new FileManager(requestElement.get("Введите название файла со скриптом:", inputData::getFileName, false), printer).getTextFileByName(), this)});
    }


    public void setInput(Scanner sc) { //todo exception
        try {
            this.sc = sc;
        } catch (Exception e) {
            printer.println("Вы ввели неправильный аргумент для считывания", ERROR);
        }
    }

    public boolean wasExit() {
        return this.running;
    }

}