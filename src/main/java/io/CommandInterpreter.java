package io;

import commands.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

import static io.ConsoleColor.RED;

public class CommandInterpreter { //TODO implements runnable
    private final Map<String, Command> commandMap;
    private final Printer printer;
    private final Map<String, Supplier<Object[]>> supplierMap;

    public CommandInterpreter(Map<String, Command> commandMap, Map<String, Supplier<Object[]>> supplierMap, Printer printer) {
        this.commandMap = commandMap;
        this.printer = printer;
        this.supplierMap = supplierMap;
    }


    public boolean run(String commandAsString, Object... scriptArgs) {
        try {
            if (!commandMap.containsKey(commandAsString)) {
                printer.println(("Команды с названием " + commandAsString + " не существует"), RED);
                return true;
            }
            Command command = commandMap.get(commandAsString);
            if (scriptArgs.length == 0) {
                return command.execute(new CommandArgument(commandAsString, supplierMap).get()); //todo mb transmit in console
            } else
                return command.execute(scriptArgs);
        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage(), RED);
//            e.printStackTrace();
        }
        return true;
    }
}
