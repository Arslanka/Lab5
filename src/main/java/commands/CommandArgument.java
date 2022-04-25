package commands;

import file.TextFile;
import io.InputData;
import io.ExScript;
import io.JsonString;
import io.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class CommandArgument {
    private final String commandAsString;
    private final Map<String, Command> commandMap;
    private final Scanner sc;

    public CommandArgument(String commandAsString, Map<String, Command> commandMap, Scanner sc) {
        this.commandAsString = commandAsString;
        this.commandMap = commandMap;
        this.sc = sc;
    }

    public Object[] get() throws FileNotFoundException {
        try {
            JsonString jsonString = new JsonString(new InputData(), sc, new Printer());
            switch (commandAsString) {
                case "add":
                case "remove_greater":
                case "add_if_max":
                case "remove_lower":
                    return new Object[]{jsonString.getDragon().build()}; //dragon
                case "update":
                    return new Object[]{jsonString.getId(), jsonString}; //id, dragon
                case "remove_by_id":
                    return new Object[]{jsonString.getId()}; //id
                case "execute_script":
                    return new Object[]{new ExScript(new TextFile(new File(sc.next())), commandMap)};
                case "remove_all_by_weight":
                    return new Object[]{jsonString.getWeight()}; //weight
                case "count_greater_than_killer":
                    return new Object[]{jsonString.getPerson().build()}; //person
                case "filter_greater_than_age":
                    return new Object[]{jsonString.getAge()}; //age
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Вы ввели некорректные данные, пожалуйста, попробуйте еще раз.");
        }
        throw new IllegalArgumentException("Ваши данные некорректны");
    }
}
