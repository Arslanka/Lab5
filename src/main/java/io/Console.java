package io;

import collection.CollectionManager;
import com.google.gson.JsonParseException;
import commands.*;
import exceptions.FileReadPermissionException;
import file.JsonFile;
import file.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static io.ConsoleColor.*;

public class Console {
    public final File file;
    private final Scanner sc = new Scanner(System.in);
    private final Map<String, Command> commandsByName = new HashMap<>();
    private final CollectionManager collectionManager;

    public Console(String fileName, CollectionManager collectionManager) {
        this.file = new File(fileName);
        this.collectionManager = collectionManager;
        fillCommands();
    }

    public void startInteractiveMode() {
        try {
            TextFile textFile = new TextFile(this.file);
            JsonFile jsonFile = new JsonFile(textFile);
            try {
                collectionManager.add(jsonFile.read());
            } catch (NoSuchElementException e) {
                System.err.println(e.getMessage());
                return;
            }
            RequestElement requestElement = new RequestElement(this.commandsByName, sc);
            boolean needExit = false;
            while (!needExit) {
                System.out.println(GREEN.wrapped("Введите команду:"));
                try {
                    needExit = requestElement.run(sc.nextLine().toLowerCase());
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException("Вы ничего не ввели. Пожалуйста введите данные.");
                }
            }
            System.out.println(YELLOW.wrapped("Исполнение программы остановлено"));
        } catch (FileReadPermissionException | IOException | JsonParseException | NoSuchElementException e) {
            System.out.println(RED.wrapped(e.getMessage()));
            new Console(sc.next(), collectionManager).startInteractiveMode();
        }
    }

    private void fillCommands() {
        commandsByName.put("help", new HelpCommand());
        commandsByName.put("exit", new HelpCommand());
        commandsByName.put("info", new InfoCommand(collectionManager));
        commandsByName.put("show", new ShowCommand(collectionManager));
        commandsByName.put("add", new AddCommand(collectionManager));
        commandsByName.put("update", new UpdateIdCommand(collectionManager));
        commandsByName.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandsByName.put("clear", new ClearCommand(collectionManager));
        commandsByName.put("save", new SaveCommand(collectionManager));
        commandsByName.put("execute_script", new ExecuteScriptCommand());
        commandsByName.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandsByName.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commandsByName.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commandsByName.put("remove_all_by_weight", new RemoveAllByWeight(collectionManager));
        commandsByName.put("count_greater_than_killer", new CountGreaterThanKillerCommand(collectionManager));
        commandsByName.put("filter_greater_than_age", new FilterGreaterThanAgeCommand(collectionManager));
    }
}