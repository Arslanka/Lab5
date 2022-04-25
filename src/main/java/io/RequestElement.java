package io;

import commands.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import static io.ConsoleColor.RED;

public class RequestElement {
    private final Map<String, Command> commandMap;
    private final Scanner sc;

    public RequestElement(Map<String, Command> commandMap, Scanner sc) {
        this.commandMap = commandMap;
        this.sc = sc;
    }


    public boolean run(String commandAsString) throws NullPointerException, FileNotFoundException {
        try {
            if (!commandMap.containsKey(commandAsString)) {
                System.out.println(RED.wrapped("Такой команды не существует. Пожалуйста, введите команду еще раз."));
                return false;
            }
            Command command = commandMap.get(commandAsString);
            if (command.withArgument()) {
                command.execute(new CommandArgument(commandAsString, commandMap, sc).get());
                return false;
            }
            if (Objects.equals(commandAsString, "exit"))
                return true;
            command.execute();
        } catch (NoSuchElementException e) {
            System.out.println(RED.wrapped(e.getMessage()));
        }
        return false;
    }
}
