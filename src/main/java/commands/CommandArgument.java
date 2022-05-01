package commands;

import file.JsonFile;
import file.TextFile;
import io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

import static io.ConsoleColor.BLUE;


public class CommandArgument {
    private final String commandAsString;
    private final Map<String, Supplier<Object[]>> stringSupplierMap;

    public CommandArgument(String commandAsString, Map<String, Supplier<Object[]>> stringSupplierMap) {
        this.commandAsString = commandAsString;
        this.stringSupplierMap = stringSupplierMap;
    }

    public Object[] get() {
        if (stringSupplierMap.containsKey(commandAsString)) {
            return stringSupplierMap.get(commandAsString).get();
        }
        throw new IllegalArgumentException("Ваши данные некорректны");
    }
}
