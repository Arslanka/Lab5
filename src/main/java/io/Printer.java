package io;


import data.Dragon;

import java.util.Collection;

public class Printer {

    public void printCollection(Collection<Dragon> collection, ConsoleColor color) {
        collection.forEach(d -> println(d, color));
    }


    public void print(Object obj, ConsoleColor color) {
        System.out.print(color.wrapped(obj.toString()));
    }

    public void println(Object obj, ConsoleColor color) {
        print(obj.toString() + '\n', color);
    }


}
