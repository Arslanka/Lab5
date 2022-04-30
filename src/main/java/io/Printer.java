package io;


import data.Dragon;

import java.io.PrintStream;
import java.util.Collection;

import static io.ConsoleColor.*;

public class Printer {

    public void printCollection(Collection<Dragon> collection, ConsoleColor color) {
        for (Dragon d : collection) {
            println(d, color);
        }
    }


    public void print(Object obj, ConsoleColor color) {
        System.out.print(color.wrapped(obj.toString()));
    }

    public void println(Object obj, ConsoleColor color) {
        print(obj.toString() + '\n', color);
    }


}
