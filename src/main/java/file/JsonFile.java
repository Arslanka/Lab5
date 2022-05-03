package file;

import com.google.gson.*;
import data.Dragon;
import io.JsonString;

import java.io.IOException;
import java.util.*;

public class JsonFile {
    private final TextFile textFile;

    public JsonFile(TextFile textFile) {
        this.textFile = textFile;
    }

    public Collection<Dragon> read() throws IOException, JsonParseException {
        try {
            return new JsonString().readCollection(textFile.read());
        } catch (IOException e) {
            throw new IOException("Невозможно прочитать файл. " + e.getMessage());
        }
    }

    public void write(Set<Dragon> dragonSet) throws IOException, JsonIOException {
        textFile.write(new JsonString().get(dragonSet));
    }

    @Override
    public String toString() {
        return "textFile = " + textFile;
    }
}
