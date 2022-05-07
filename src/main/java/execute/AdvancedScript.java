package execute;

import com.google.gson.JsonParseException;
import commands.*;
import file.JsonFile;
import file.TextFile;
import io.JsonString;
import io.Printer;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

import static execute.Script.executableScripts;


public class AdvancedScript {
    private final TextFile textFile;
    private final Map<String, Command> commandMap;
    private final Map<String, Supplier<Object[]>> supplierMap;
    private final Printer printer;

    public AdvancedScript(TextFile textFile, Map<String, Command> commandMap, Map<String, Supplier<Object[]>> supplierMap, Printer printer) {
        this.textFile = textFile;
        this.commandMap = commandMap;
        this.supplierMap = supplierMap;
        this.printer = printer;
    }

    public boolean execute() throws IOException { //todo exceptions
        if (executableScripts.contains(textFile.getFile()))
            throw new IllegalStateException("Вы находитесь в процессе выполнения скрипта " + textFile + ". Ошибка выполнения скрипта");
        executableScripts.add(textFile.getFile());
        ArrayList<String> stringRep = new ArrayList<>();
        Arrays.stream(textFile.read().split("\\s+")).filter(s -> !s.isEmpty()).forEach(stringRep::add);
        StringBuilder data = new StringBuilder();
        String lastCommand = null;
        CommandInterpreter commandInterpreter = new CommandInterpreter(commandMap, supplierMap, printer);
        boolean isFirst = true, noExit = true;
        for (String s : stringRep) {
            if (!noExit) {
                executableScripts.remove(this.textFile.getFile());
                return false;
            }
            if (!commandMap.containsKey(s)) {
                data.append(s);
                data.append(' ');
                continue;
            }
            if (isFirst) {
                if (!data.toString().isEmpty()) {
                    executableScripts.remove(this.textFile.getFile());
                    throw new ClassCastException("Вы ввели неверные данные для скрипта");
                }
                lastCommand = s;
                isFirst = false;
                continue;
            }
            if (!commandMap.get(lastCommand).withArgument()) {
                if (!data.toString().isEmpty()) {
                    executableScripts.remove(this.textFile.getFile());
                    throw new ClassCastException("Вы ввели неверные данные для скрипта");
                }
                noExit = commandInterpreter.run(lastCommand);
            } else {
                if (data.toString().isEmpty()) commandInterpreter.run(lastCommand);
                else {
                    noExit = commandInterpreter.run(lastCommand, getCommandArgs(commandMap.get(lastCommand), data));
                    data = new StringBuilder();
                }
            }
            lastCommand = s;
        }
        if (!noExit) {
            executableScripts.remove(this.textFile.getFile());
            return false;
        }
        if (!commandMap.get(lastCommand).withArgument()) {
            if (!data.toString().isEmpty()) {
                executableScripts.remove(this.textFile.getFile());
                throw new ClassCastException("Вы ввели неверные данные для скрипта");
            }
            commandInterpreter.run(lastCommand);
        } else {
            commandInterpreter.run(lastCommand, getCommandArgs(commandMap.get(lastCommand), data));
        }
        executableScripts.remove(this.textFile.getFile());
        return false;
    }

    private Object[] getCommandArgs(Command command, StringBuilder data) { //todo excep
        Class<?>[] commandArgsClasses = command.getArgumentsClasses();
        ArrayList<Object> commandArgs = new ArrayList<>();
        int start = 0;
        for (Class<?> arg : commandArgsClasses) {
            StringBuilder argAsString = new StringBuilder();
            JsonString jsonString = new JsonString();
            if (command instanceof SaveCommand) {
                try {
                    commandArgs.add(new JsonFile(new TextFile(new File(data.toString().trim()))));
                    break;
                } catch (ClassCastException | JsonParseException | IOException ignored) {
                }
            } else if (command instanceof ExecuteAdvancedScriptCommand) {
                try {
                    commandArgs.add(new AdvancedScript(new TextFile(new File(data.toString().trim())), commandMap, supplierMap, printer));
                    break;
                } catch (ClassCastException | JsonParseException | IOException ignored) {
                }
            } else {
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
        }
        if (commandArgs.size() != commandArgsClasses.length && !data.toString().isEmpty())
            throw new JsonParseException("Вы ввели неверные аргументы в json в команде " + command.getName());
        return commandArgs.toArray();
    }

}


