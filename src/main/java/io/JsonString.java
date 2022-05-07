package io;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import data.Dragon;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class JsonString {
    public <T> T read(String string, Class<T> cls) throws IOException, JsonParseException {
        T t;
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class
                    , (JsonDeserializer<ZonedDateTime>)
                            (json, type, jsonPrimitive)
                                    -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
            t = gson.fromJson(string, cls);
        } catch (JsonParseException | DateTimeParseException e) {
            throw new JsonParseException("Ошибка парсинга Json. Данные некорректны. " + e.getMessage());
        }
        return t;
    }

    public Collection<Dragon> readCollection(String string) throws IOException, JsonParseException { //TODO output single El
        Dragon[] dragons;
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class
                    , (JsonDeserializer<ZonedDateTime>)
                            (json, type, jsonPrimitive)
                                    -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();

            dragons = gson.fromJson(string, Dragon[].class);
        } catch (JsonParseException | DateTimeParseException e) {
            throw new JsonParseException("Ошибка парсинга Json. Данные некорректны. " + e.getLocalizedMessage());
        }
        return new ArrayList<>(Arrays.asList(dragons));
    }

    public String get(Set<Dragon> dragonSet) throws IOException, JsonIOException { //TODO input single dragon
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>)
                            (date, type, jsonSerializationContext)
                                    -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)))
                    .setPrettyPrinting().create();
            return (gson.toJson(dragonSet));
        } catch (JsonParseException e) {
            throw new IOException("Ошибка конвертации в Json. Данные в коллекции некорректны");
        }
    }

    public <T> String get(Class<T> t) throws IOException, JsonIOException { //TODO input single dragon
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>)
                            (date, type, jsonSerializationContext)
                                    -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)))
                    .setPrettyPrinting().create();
            return (gson.toJson(t));
        } catch (JsonParseException e) {
            throw new IOException("Ошибка конвертации в Json. Данные в коллекции некорректны");
        }
    }
}
