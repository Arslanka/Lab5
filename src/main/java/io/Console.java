package io;

import collection.CollectionManager;
import com.google.gson.JsonParseException;
import commands.*;
import exceptions.FileReadPermissionException;
import file.JsonFile;
import file.TextFile;

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
    public final File file;
    private final Scanner sc = new Scanner(System.in);
    private final Map<String, Command> commandsByName = new HashMap<>();
    private final Map<String, Supplier<Object[]>> supplierMap = new HashMap<>();
    private final CollectionManager collectionManager;
    private final Printer printer;

    public Console(String fileName, CollectionManager collectionManager, Printer printer) {
        this.file = new File(fileName);
        this.collectionManager = collectionManager;
        this.printer = printer;
        fillCommands();
    }

    public void startInteractiveMode() {
        try {
            TextFile textFile = new TextFile(this.file);
            JsonFile jsonFile = new JsonFile(textFile);
            try {
                collectionManager.add(jsonFile.read());
            } catch (NoSuchElementException e) {
                printer.println(e.getMessage(), RED);
                return;
            }
            RequestElement requestElement = new RequestElement(this.commandsByName, supplierMap, sc, this.printer);
            boolean needExit = false;
            while (!needExit) {
                printer.println(("Введите команду:"), GREEN);
                try {
                    needExit = requestElement.run(sc.nextLine().trim().toLowerCase());
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException("Вы ничего не ввели. Пожалуйста введите данные.");
                }
            }
            printer.println("Исполнение программы остановлено", YELLOW);
        } catch (FileReadPermissionException | IOException | JsonParseException e) {
            printer.print((e.getMessage()), RED);
            new Console(sc.next(), collectionManager, printer).startInteractiveMode();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Вы ничего не ввели. Пожалуйста введите данные.");
        }
    }

    private void fillCommands() {
        JsonString jsonString = new JsonString(new InputData(), sc, printer);
        commandsByName.put("help", new HelpCommand(new Printer()));
        supplierMap.put("help", () -> new Object[]{commandsByName});
        commandsByName.put("info", new InfoCommand(collectionManager));
        supplierMap.put("info", () -> new Object[]{});
        commandsByName.put("show", new ShowCommand(collectionManager));
        supplierMap.put("show", () -> new Object[]{});
        commandsByName.put("add", new AddCommand(collectionManager));
        supplierMap.put("add", () -> new Object[]{jsonString.getDragon().build()});
        commandsByName.put("update", new UpdateIdCommand(collectionManager));
        supplierMap.put("update", () -> new Object[]{jsonString.getId(), jsonString.getDragon().build()});
        commandsByName.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        supplierMap.put("remove_by_id", () -> new Object[]{jsonString.getId()});
        commandsByName.put("clear", new ClearCommand(collectionManager));
        supplierMap.put("clear", () -> new Object[]{});
        commandsByName.put("save", new SaveCommand(collectionManager));
        supplierMap.put("save", () -> {
            try {
                System.out.print(BLUE.wrapped("Введите название файла:")); //todo fix
                return new Object[]{new JsonFile(new TextFile(new File(sc.nextLine().trim())))};
            } catch (FileNotFoundException e) {
                printer.println(e.getMessage(), RED);
            }
            return null; //TODO FIX
        });
        commandsByName.put("execute_script", new ExecuteScriptCommand());
        //supplierMap.put("execute_script", () -> new Object[]{jsonString.getDragon().build()});
        commandsByName.put("add_if_max", new AddIfMaxCommand(collectionManager));
        supplierMap.put("add_if_max", () -> new Object[]{jsonString.getDragon().build()});
        commandsByName.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        supplierMap.put("add", () -> new Object[]{jsonString.getDragon().build()});
        commandsByName.put("remove_lower", new RemoveLowerCommand(collectionManager));
        supplierMap.put("remove_lower", () -> new Object[]{jsonString.getDragon().build()});
        commandsByName.put("remove_all_by_weight", new RemoveAllByWeight(collectionManager));
        supplierMap.put("remove_all_by_weight", () -> new Object[]{jsonString.getWeight()});
        commandsByName.put("count_greater_than_killer", new CountGreaterThanKillerCommand(collectionManager));
        supplierMap.put("count_greater_than_killer", () -> new Object[]{jsonString.getPerson().build()});
        commandsByName.put("filter_greater_than_age", new FilterGreaterThanAgeCommand(collectionManager));
        supplierMap.put("filter_greater_than_age", () -> new Object[]{jsonString.getAge()});
    }

}