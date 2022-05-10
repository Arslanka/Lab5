package commands;

import io.Printer;

import static io.ConsoleColor.ERROR;

public class ExitCommand implements Command {
    private final Printer printer;

    public ExitCommand(Printer printer) {
        this.printer = printer;
    }


    @Override
    public boolean execute(Object... args) {
        printer.println("The program is completed", ERROR);
        return false;
    }

    @Override
    public boolean withArgument() {
        return false;
    }

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public String getDescription() {
        return "Stops program execution";
    }

    @Override
    public Class<?>[] getArgumentsClasses() {
        return new Class[]{};
    }
}
