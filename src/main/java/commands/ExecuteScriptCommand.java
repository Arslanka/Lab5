package commands;

public class ExecuteScriptCommand implements Command {

    @Override
    public void execute(Object... args) {

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
}
