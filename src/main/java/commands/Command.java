package commands;

import exceptions.ExecutionException;
import exceptions.ExistingIdException;

public interface Command {
    boolean execute(Object... args) throws ExecutionException, ExistingIdException;

    boolean withArgument();

    String getName();

    String getDescription();

    Class<?>[] getArgumentsClasses();
}
