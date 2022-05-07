package execute;

import commands.*;
import io.Printer;

import java.util.Map;
import java.util.function.Supplier;

import static io.ConsoleColor.ERROR;

public class CommandInterpreter {
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
                printer.println(("Команды с названием " + commandAsString + " не существует"), ERROR);
                return true;
            }
            Command command = commandMap.get(commandAsString);
            if (scriptArgs.length == 0) {
                return command.execute(new CommandArgument(commandAsString, supplierMap).get());
            } else
                return command.execute(scriptArgs);
        } catch (IllegalStateException e) {
            printer.println(e.getMessage(), ERROR);
        }
        return true;
    }
}
