package io;


import data.Dragon;

import java.util.Collection;
import java.util.Scanner;

public class Printer {
    private boolean scriptMode = false;

    public Printer(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

    public Printer() {
    };

    public void printCollection(Collection<Dragon> collection, ConsoleColor color) {
        if (!scriptMode)
            collection.forEach(d -> println(d, color));
    }


    public void print(Object obj, ConsoleColor color) {
        if (!scriptMode)
            System.out.print(color.wrapped(obj.toString()));
    }

    public void println(Object obj, ConsoleColor color) {
        if (!scriptMode)
            System.out.println(color.wrapped(obj.toString()));
    }


}
