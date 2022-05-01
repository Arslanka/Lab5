package io;

import commands.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

import static io.ConsoleColor.RED;

public class ExCommand { //TODO implements runnable
    private final Map<String, Command> commandMap;
    private final Printer printer;
    private final Map<String, Supplier<Object[]>> supplierMap;

    public ExCommand(Map<String, Command> commandMap, Map<String, Supplier<Object[]>> supplierMap, Scanner sc, Printer printer) {
        this.commandMap = commandMap;
        this.printer = printer;
        this.supplierMap = supplierMap;
    }


    public boolean run(String commandAsString) throws NullPointerException, FileNotFoundException { //todo printer
        try {
            if (Objects.equals(commandAsString, "exit"))
                return true;
            if (!commandMap.containsKey(commandAsString)) {
                printer.println(("Такой команды не существует. Пожалуйста, введите команду еще раз."), RED);
                return false;
            }
            Command command = commandMap.get(commandAsString);
            if (command.withArgument()) {
                command.execute(new CommandArgument(commandAsString, supplierMap).get());
                return false;
            }
            command.execute();
        } catch (NoSuchElementException e) {
            printer.println(e.getMessage(), RED);
        }
        return false;
    }
}
