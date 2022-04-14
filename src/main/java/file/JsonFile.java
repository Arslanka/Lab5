package file;

import com.google.gson.*;
import data.Dragon;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JsonFile {
    private final TextFile textFile;

    public JsonFile(TextFile textFile) {
        this.textFile = textFile;
    }

    public List<Dragon> read() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class
                , (JsonDeserializer<ZonedDateTime>)
                        (json, type, jsonPrimitive)
                                -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
        Dragon[] dragons = gson.fromJson(textFile.read(), Dragon[].class);
        return new ArrayList<>(Arrays.asList(dragons));
    }

    public String asString() throws IOException {
        return textFile.read();
    }

    public void write(Set<Dragon> dragonSet) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>)
                        (date, type, jsonSerializationContext)
                                -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)))
                .setPrettyPrinting().create();
        textFile.write(gson.toJson(dragonSet));
    }
}
