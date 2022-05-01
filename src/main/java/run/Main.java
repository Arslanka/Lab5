package run;


import collection.CollectionManager;
import io.Console;
import io.Printer;

import java.util.*;

import static io.ConsoleColor.RED;
import static io.ConsoleColor.YELLOW;

public class Main {
    public static void main(String[] args) { //TODO args input + try catch nlpexc
        Console console = new Console(new Scanner(System.in), new CollectionManager(), new Printer());
        console.startInteractiveMode();
    }
}