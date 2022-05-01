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

    public <T> void get(String prefix, Function<String, T> cast, Consumer<T> action) {
        while (true) {
            try {
                printer.print(prefix, PURPLE);
                action.accept(cast.apply(sc.nextLine().trim().toLowerCase()));
                return;
            } catch (IllegalStateException | IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }

    public <T>  T get(String prefix, Function<String, T> action) {
        while (true) {
            try {
                printer.print(prefix, PURPLE);
                return action.apply(sc.nextLine().trim().toLowerCase());
            } catch (IllegalStateException | IllegalArgumentException | InputMismatchException e) {
                printer.println(e.getMessage(), RED);
            }
        }
    }
}
