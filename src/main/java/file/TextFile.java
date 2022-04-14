package file;

import exceptions.FileReadPermissionException;
import io.IReadable;
import io.IWriteable;
import sun.text.normalizer.UTF16;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextFile implements IReadable, IWriteable {
    private final File file;

    public TextFile(File file) throws FileNotFoundException, FileReadPermissionException {
        this.file = file;
        if (!file.exists()) {
            throw new FileNotFoundException("Файла с таким названием не существует");
        }
        if (file.exists() && file.isDirectory()) {
            throw new FileNotFoundException("По введенному пути находится директория, а не файл");
        }
        if (!file.canRead()) {
            throw new FileReadPermissionException("Нет прав для чтения файла"); //TODO FileReadPermissionException
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
        }
        in.close();
        return builder.toString();
    }

    @Override
    public void write(String string) throws IOException {
        final OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(this.file)
                , StandardCharsets.UTF_16);
        streamWriter.write(string);
        streamWriter.close();
    }
}
