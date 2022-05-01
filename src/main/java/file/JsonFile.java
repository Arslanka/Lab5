package file;

import collection.CollectionManager;
import com.google.gson.*;
import data.Dragon;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.jar.JarException;

public class JsonFile {
    private final TextFile textFile;

    public JsonFile(TextFile textFile) {
        this.textFile = textFile;
    }

    public Collection<Dragon> read() throws IOException, JsonParseException {
        Dragon[] dragons;
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class
                    , (JsonDeserializer<ZonedDateTime>)
                            (json, type, jsonPrimitive)
                                    -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();

            dragons = gson.fromJson(textFile.read(), Dragon[].class);
        } catch (JsonParseException | DateTimeParseException e) {
            throw new JsonParseException("Ошибка парсинга json-файла: данные в файле некорректны.");
        } catch (IOException e) {
            throw new IOException("Невозможно прочитать файл. " + e.getMessage());
        }
        return new ArrayList<>(Arrays.asList(dragons));
    }

    public void write(Set<Dragon> dragonSet) throws IOException, JsonIOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>)
                        (date, type, jsonSerializationContext)
                                -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)))
                .setPrettyPrinting().create();
        textFile.write(gson.toJson(dragonSet));
    }

    @Override
    public String toString() {
        return "textFile=" + textFile;
    }
}
