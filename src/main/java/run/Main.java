package run;


import collection.CollectionManager;
import io.Console;
import io.Printer;

import java.util.*;

import static io.ConsoleColor.RED;
import static io.ConsoleColor.YELLOW;

public class Main {

    public static void main(String[] args) { //TODO TRYCATCH
        System.out.println(YELLOW.wrapped("Введите название файла, данными из которого будет заполнена коллекция"));
        try {
            Scanner scanner = new Scanner(System.in);
            String arg = scanner.nextLine();
            //args
            if (arg.isEmpty()) {
                System.out.println(RED.wrapped("Вы не ввели название файла. Пожалуйста, попробуйте еще раз."));
            } else {
                Console console = new Console(arg, new CollectionManager(), new Printer());
                console.startInteractiveMode();
            }
        } catch (NoSuchElementException e) {
            System.out.println(RED.wrapped("Вы ничего не ввели. Попробуйте еще раз."));
        }
    }
}