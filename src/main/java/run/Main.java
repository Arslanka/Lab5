package run;


import collection.CollectionManager;
import data.Dragon;
import io.Console;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String ANSI_YELLOW = "\u001B[33m";


    public static void main(String[] args) { //TODO TRYCATCH
        System.out.println(ANSI_YELLOW + "Введите название файла, данными из которого будет заполнена коллекция");
        Scanner scanner = new Scanner(System.in);
        String arg = scanner.nextLine();
        //args
        if (arg.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Вы не ввели название файла. Пожалуйста, попробуйте еще раз.");
        } else {
            Console console = new Console(arg, new CollectionManager());
            console.startInteractiveMode();
        }
    }
}