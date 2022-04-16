package io;

import collection.CollectionManager;
import collection.CommandManager;
import com.google.gson.JsonParseException;
import exceptions.FileReadPermissionException;
import file.JsonFile;
import file.TextFile;
import io.RequestElement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Console {
    public final File file;
    private final Scanner sc = new Scanner(System.in);

    public Console(String fileName) {
        this.file = new File(fileName);
    }

    public void startInteractiveMode() {
        try {
            TextFile textFile = new TextFile(this.file);
            JsonFile jsonFile = new JsonFile(textFile);
            CollectionManager collectionManager = new CollectionManager(jsonFile.read());
            CommandManager commandManager = new CommandManager(collectionManager);
            boolean needExit = false;
            while (!needExit) {
                System.out.println("Введите команду:");
                needExit = commandManager.executeCommand(new RequestElement(sc.nextLine()), collectionManager);
            }
            System.out.println("Исполнение программы остановлено");
        } catch (FileReadPermissionException | IOException | JsonParseException e) {
            System.err.println(e.getMessage());
        }
    }
}