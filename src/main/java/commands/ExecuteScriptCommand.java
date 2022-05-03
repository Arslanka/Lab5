package commands;

import file.JsonFile;
import file.TextFile;
import io.Printer;
import io.Script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static io.Console.SEPARATOR;
import static io.ConsoleColor.RED;

public class ExecuteScriptCommand implements Command {
    private final Printer printer;

    public ExecuteScriptCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Script script = (Script) args[0];
            script.execute();
            printer.println(SEPARATOR, RED);
        } catch (IOException | ClassCastException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Вы ввели неверный аргумент для команды");
        }
        return true;
    }

    @Override
    public boolean withArgument() {
        return true;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{JsonFile.class};
    }
}
