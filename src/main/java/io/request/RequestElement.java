package io.request;

import io.Printer;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.ConsoleColor.*;

public class RequestElement {
    private final Scanner sc;
    private final Printer printer;

    public RequestElement(Scanner sc, Printer printer) {
        this.sc = sc;
        this.printer = printer;
    }

    public <T> void get(String prefix, Function<String, T> cast, Consumer<T> action, boolean toLower) {
        while (true) {
            try {
                printer.print(prefix, FIELD);
                if (toLower)
                    action.accept(cast.apply(sc.nextLine().trim().toLowerCase()));
                else
                    action.accept(cast.apply(sc.nextLine().trim()));
                return;
            } catch (IllegalStateException | IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), ERROR);
            }
        }
    }

    public <T> T get(String prefix, Function<String, T> action, boolean toLower) {
        while (true) {
            try {
                printer.print(prefix, FIELD);
                if (toLower)
                    return action.apply(sc.nextLine().trim().toLowerCase());
                else return action.apply(sc.nextLine().trim());

            } catch (IllegalStateException | IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), ERROR);
            }
        }
    }
}
