package commands;

public interface Command {
    boolean execute(Object... args);

    boolean withArgument();

    String getName();

    String getDescription();

    Class<?>[] getArgumentsClasses();
}
