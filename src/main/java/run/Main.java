package run;


import collection.Collection;
import io.Console;
import io.Printer;

import java.util.*;

public class Main {
    public static void main(String[] args) { //TODO args input + try catch nlpexc
        Console console = new Console(new Scanner(System.in), new Collection(), new Printer());
        console.startInteractiveMode();
    }
}