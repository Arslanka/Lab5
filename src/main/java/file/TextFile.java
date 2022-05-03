package file;

import exceptions.FileReadPermissionException;
import io.Readable;
import io.Writeable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;

public class TextFile implements Readable, Writeable {
    private final File file;
    private static final int MAX_FILE_LENGTH = 10000;

    public TextFile(File file) throws FileNotFoundException, FileReadPermissionException {
        // TODO: specify file size limit (/dev/random)
        this.file = file;
        if (!file.exists()) {
            throw new FileNotFoundException("Файла с таким названием не существует. Пожалуйста, введите корректные данные");
        }
        if (file.isDirectory()) {
            throw new FileNotFoundException("По введенному пути находится директория, а не файл. Пожалуйста, введите корректные данные");
        }
        if (!file.canRead()) {
            throw new FileReadPermissionException("Нет прав для чтения файла. Пожалуйста, введите корректные данные"); //TODO FileReadPermissionException
        }
    }

    @Override
    public String read() throws IOException { //TODO Exceptions, emptyFile;
        final StringBuilder builder = new StringBuilder();
        final Reader in = new FileReader(file);
        while (true) {
            int ch = in.read();
            if (ch == -1) {
                break;
            }
            builder.append((char) ch);
            if (builder.length() > MAX_FILE_LENGTH)
                throw new FileSystemException("Количество символов в файле превышает лимит – " + MAX_FILE_LENGTH);
        }
        in.close();
        if (builder.toString().isEmpty()) {
            throw new IOException("Файл пуст. Пожалуйста, заполните его данными.");
        }
        return builder.toString();
    }

    @Override
    public void write(String string) throws IOException {
        final OutputStreamWriter streamWriter
                = new OutputStreamWriter(
                new FileOutputStream(this.file), StandardCharsets.UTF_8);
        streamWriter.write(string);
        streamWriter.close();
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
