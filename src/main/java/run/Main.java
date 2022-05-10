package run;


import collection.Collection;
import io.Application;
import io.Printer;

import java.util.*;

public class Main {
    public static void main(String[] args) { //TODO args input + try catch nlpex/c
        Application application = new Application(args, new Collection(), new Printer(false));
        application.startInteractiveMode();
    }
}