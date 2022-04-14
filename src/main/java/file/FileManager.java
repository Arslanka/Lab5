package file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private final File file;

    public FileManager(File file) throws FileNotFoundException {
        this.file = file;
        if (!file.exists()) {
            throw new FileNotFoundException("Файла с таким названием не существует");
        }
        if (file.exists() && file.isDirectory()) {
            throw new FileNotFoundException("По введенному пути находится директория, а не файл");
        }
        /*
        if (!file.canRead()) {
            throw new FileReadPermissionException("Нет прав для чтения файла"); //TODO FileReadPermissionException
        }
         */
    }

    public String read() {
        final StringBuilder outputStringBuilder = new StringBuilder();
        try (final FileReader reader = new FileReader(this.file)) {
            while (reader.ready()) {
                outputStringBuilder.append((char) reader.read());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return outputStringBuilder.toString();

    }


    public void save(String inputString) {
        try (final OutputStreamWriter streamWriter
                     = new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_16)) {
            streamWriter.write(inputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> fileToList() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
