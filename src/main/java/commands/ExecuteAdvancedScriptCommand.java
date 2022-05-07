package commands;

import io.Printer;
import execute.AdvancedScript;

import java.io.File;
import java.io.IOException;

public class ExecuteAdvancedScriptCommand implements Command {
    private final Printer printer;

    public ExecuteAdvancedScriptCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public boolean execute(Object... args) {
        try {
            AdvancedScript script = (AdvancedScript) args[0];
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
        return "execute_advanced_script";
    }

    @Override
    public String getDescription() {
        return "Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды, объекты в формате json";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{File.class};
    }
}
