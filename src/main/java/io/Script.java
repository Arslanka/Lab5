package io;

import com.google.gson.JsonParseException;
import commands.*;
import data.Dragon;
import file.JsonFile;
import file.TextFile;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

import static io.ConsoleColor.RED;

public class Script {
    private final TextFile textFile;
    private final Map<String, Command> commandMap;
    private final Map<String, Supplier<Object[]>> supplierMap;
    private final Printer printer;

    public Script(TextFile textFile, Map<String, Command> commandMap, Map<String, Supplier<Object[]>> supplierMap, Printer printer) {
        this.textFile = textFile;
        this.commandMap = commandMap;
        this.supplierMap = supplierMap;
        this.printer = printer;
    }

    public void execute() throws IOException { //todo exceptions
        ArrayList<String> stringRep = new ArrayList<>();
        Arrays.stream(textFile.read().split("\\s+")).filter(s -> !s.isEmpty()).forEach(stringRep::add);
        StringBuilder data = new StringBuilder();
        String lastCommand = null;
        CommandInterpreter commandInterpreter = new CommandInterpreter(commandMap, supplierMap, printer);
        boolean isFirst = true, noExit = true;
        for (String s : stringRep) {
            if (!commandMap.containsKey(s)) {
                data.append(s);
                data.append(' ');
                continue;
            }
            if (isFirst) {
                if (!data.toString().isEmpty()) throw new ClassCastException("Вы ввели неверные данные для скрипта");
                lastCommand = s;
                isFirst = false;
                continue;
            }
            if (!commandMap.get(lastCommand).withArgument()) {
                if (!data.toString().isEmpty()) throw new ClassCastException("Вы ввели неверные данные для скрипта");
                noExit &= commandInterpreter.run(lastCommand);
            } else {
                if (data.toString().isEmpty()) commandInterpreter.run(lastCommand);
                else {
                    noExit &= commandInterpreter.run(lastCommand, getCommandArgs(commandMap.get(lastCommand), data));
                    data = new StringBuilder();
                }
            }
            lastCommand = s;
        }
        if (!commandMap.get(lastCommand).withArgument() && noExit) {
            if (!data.toString().isEmpty()) throw new ClassCastException("Вы ввели неверные данные для скрипта");
            commandInterpreter.run(lastCommand);
        } else if (noExit) {
            commandInterpreter.run(lastCommand, getCommandArgs(commandMap.get(lastCommand), data));
        }
    }

    private Object[] getCommandArgs(Command command, StringBuilder data) { //todo excep
        Class<?>[] commandArgsClasses = command.getArgumentsClasses();
        ArrayList<Object> commandArgs = new ArrayList<>();
        int start = 0;
        for (Class<?> arg : commandArgsClasses) {
            StringBuilder argAsString = new StringBuilder();
            JsonString jsonString = new JsonString();
            for (; start < data.length(); ++start) {
                if (data.charAt(start) == ' ') {
                    try {
                        Object obj = jsonString.read(argAsString.toString(), arg);
                        commandArgs.add(obj);
                        ++start;
                        break;
                    } catch (JsonParseException | IOException ignored) {
                    }
                } else {
                    argAsString.append(data.charAt(start));
                }
            }
        }
        return commandArgs.toArray();
    }

}


