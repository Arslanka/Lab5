package commands;

public interface Command {
    void execute(Object... args);

    boolean withArgument();

    String getName();

    String getDescription();
}
