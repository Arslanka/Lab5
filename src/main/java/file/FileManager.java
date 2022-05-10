package file;

import exceptions.FileReadPermissionException;
import io.Printer;

import java.io.File;
import java.io.FileNotFoundException;

import static io.ConsoleColor.ERROR;

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
            printer.println(e.getMessage(), ERROR);
        } catch (NullPointerException e) {
            printer.println("You didn't enter the file name", ERROR);
        }
        return null;
    }

    public JsonFile getJsonFileByName() {
        try {
            return new JsonFile(this.getTextFileByName());
        } catch (NullPointerException e) {
            printer.println(e.getMessage(), ERROR);
        }
        return null;
    }

}
