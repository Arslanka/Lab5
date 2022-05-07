package commands;

import execute.AdvancedScript;
import execute.Script;
import io.Printer;

import java.io.File;
import java.io.IOException;

public class ExecuteScriptCommand implements Command {
    private final Printer printer;

    public ExecuteScriptCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            Script script = (Script) args[0];
            return script.execute();
        } catch (IOException | ClassCastException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Вы ввели неверный аргумент для команды");
        }
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
        return new Class[]{File.class};
    }
}
