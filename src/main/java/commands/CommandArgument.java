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
        try {
            if (stringSupplierMap.containsKey(commandAsString)) {
                return stringSupplierMap.get(commandAsString).get();
            }
//            switch (commandAsString) {
//                case "add":
//                case "remove_greater":
//                case "add_if_max":
//                case "remove_lower":
//                    return new Object[]{jsonString.getDragon().build()}; //dragon
//                case "update":
//                    return new Object[]{jsonString.getId(), jsonString}; //id, dragon
//                case "remove_by_id":
//                    return new Object[]{jsonString.getId()}; //id
//                case "execute_script":
//                    return new Object[]{new ExScript(new TextFile(new File(sc.nextLine().trim())), commandMap)};
//                case "remove_all_by_weight":
//                    return new Object[]{jsonString.getWeight()}; //weight
//                case "count_greater_than_killer":
//                    return new Object[]{jsonString.getPerson().build()}; //person
//                case "filter_greater_than_age":
//                    return new Object[]{jsonString.getAge()}; //age
//                case "help":
//                    return new Object[]{commandMap};
//                case "save":
//                    System.out.print(BLUE.wrapped("Введите название файла:")); //todo fix
//                    return new Object[]{new JsonFile(new TextFile(new File(sc.nextLine().trim())))};
//            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Вы ввели некорректные данные, пожалуйста, попробуйте еще раз.");
        }
        throw new IllegalArgumentException("Ваши данные некорректны");
    }
}
