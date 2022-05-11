package execute;

import commands.*;
import exceptions.*;
import io.Printer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static io.ConsoleColor.ERROR;

public class CommandInterpreter {
    private final Map<String, Command> commandMap;
    private final Printer printer;
    private final Map<String, Supplier<Object[]>> supplierMap;
    private final Map<Class<?>, BiFunction<Scanner, Printer, Object>> requestMap;

    public CommandInterpreter(Map<String, Command> commandMap,
                              Map<String, Supplier<Object[]>> supplierMap,
                              Printer printer, Map<Class<?>, BiFunction<Scanner, Printer, Object>> requestMap) {
        this.commandMap = commandMap;
        this.printer = printer;
        this.supplierMap = supplierMap;
        this.requestMap = requestMap;
    }


    public boolean run(ArrayList<String> commandWithArgs, Object... scriptArgs) {
        try {
            if (!commandMap.containsKey(commandWithArgs.get(0).toLowerCase())) {
                throw new InvalidCommandNameException("Command with the name " + commandWithArgs.get(0) + " does not exist.");
            }
            Command command = commandMap.get(commandWithArgs.get(0).toLowerCase());
            if (scriptArgs.length == 0 && commandWithArgs.size() == 1) {
                return command.execute(new CommandArguments(commandWithArgs.get(0), supplierMap).get());
            } else if (scriptArgs.length == 0) {
                commandWithArgs.remove(0);
                return command.execute(new CommandArguments(commandMap, supplierMap, command, requestMap).get(commandWithArgs));
            } else
                return command.execute(scriptArgs);
        } catch (ExistingIdException | InputOutputException | IncorrectIdException | ObjectBuildException | ExecutionException e) {
            printer.println(e.getMessage(), ERROR);
        }
        return true;
    }
}
