package file;

import exceptions.FileReadPermissionException;
import io.Console;
import io.ConsoleColor;
import io.Printer;

import java.io.File;
import java.io.FileNotFoundException;

import static io.ConsoleColor.BLUE;
import static io.ConsoleColor.RED;

public class FileManager {
    private final String fileName;
    private final Printer printer;

    public FileManager(String fileName, Printer printer) {
        this.fileName = fileName;
        this.printer = printer;
    }

    public TextFile getTextFileByName() {
        try {
            return new TextFile(new File(this.fileName));
        } catch (FileNotFoundException | FileReadPermissionException e) {
            printer.println(e.getMessage(), RED);
        }
        return null;
    }

    public JsonFile getJsonFileByName() {
        try {
            return new JsonFile(this.getTextFileByName());
        } catch (FileReadPermissionException e) {
            printer.println(e.getMessage(), RED);
        }
        return null;
    }

}
