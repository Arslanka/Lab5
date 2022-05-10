package io;

import collection.Collection;
import com.google.gson.JsonParseException;
import commands.*;
import data.Coordinates;
import data.Dragon;
import data.Location;
import data.Person;
import exceptions.InvalidCommandNameException;
import exceptions.RecursiveCallException;
import execute.CommandInterpreter;
import execute.AdvancedScript;
import file.FileManager;
import file.JsonFile;
import file.TextFile;
import io.request.*;

import java.io.File;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static io.ConsoleColor.*;

public class Application {
    public static final String SEPARATOR = "-----------------------";

    private final Scanner sc = new Scanner(System.in);
    private final Map<String, Command> commandsByName = new HashMap<>();
    private final Map<String, Supplier<Object[]>> supplierMap = new HashMap<>();
    private final Map<Class<?>, BiFunction<Scanner, Printer, Object>> requestMap = new HashMap<>();
    private final Collection collection;
    private final String[] fileName;
    private final Printer printer;

    public Application(String[] fileName, Collection collection, Printer printer) {
        this.fileName = fileName;
        this.collection = collection;
        this.printer = printer;
        fillCommands();
    }

    public void startInteractiveMode() {
        try {
            String fileName = this.fileName[0];
            final File file = new File(fileName.trim());
            final TextFile textFile = new TextFile(file);
            final JsonFile jsonFile = new JsonFile(textFile);
            collection.add(jsonFile.read());
            CommandInterpreter commandInterpreter = new CommandInterpreter(this.commandsByName, supplierMap, this.printer, requestMap);
            boolean running = true;
            while (running) {
                printer.println(("Enter the command:"), REQUEST);
                try {
                    running = commandInterpreter.run(new ArrayList<>(Arrays.asList(sc.nextLine().trim().split("\\s+"))));
                } catch (NoSuchElementException e) {
                    running = false;
                } catch (RecursiveCallException | InvalidCommandNameException | IllegalArgumentException | JsonParseException e) {
                    printer.println(e.getMessage(), ERROR);
                }
            }
            printer.println("Program execution stopped", CONSOLE);
        } catch (NoSuchElementException e) {
            printer.println("The program ended incorrectly. Please restart the program.", ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.println("You didn't specify the file name to populate the collection", ERROR);
        } catch (Exception e) {
            printer.println(e.getMessage(), ERROR);
        }
    }

    public void fillCommands() {
        RequestElement requestElement = new RequestElement();
        InputData inputData = new InputData();
        commandsByName.put("help", new HelpCommand(new Printer(false)));
        supplierMap.put("help",
                () -> new Object[]{commandsByName});
        commandsByName.put("exit", new ExitCommand(new Printer(false)));
        supplierMap.put("exit",
                () -> new Object[]{});
        commandsByName.put("info",
                new InfoCommand(collection, printer));
        supplierMap.put("info",
                () -> new Object[]{});
        commandsByName.put("show", new ShowCommand(collection, printer));
        supplierMap.put("show",
                () -> new Object[]{});
        commandsByName.put("add", new AddCommand(collection, printer));
        supplierMap.put("add",
                () -> new Object[]{new RequestDragon(requestElement, sc, printer, inputData).get().build()});
        commandsByName.put("update", new UpdateIdCommand(collection, printer));
        supplierMap.put("update",
                () -> new Object[]{collection.validatedId(requestElement.get("Enter id:", sc, printer, inputData::getId, true)),
                        new RequestDragon(requestElement, sc, printer, inputData).get().build()});
        commandsByName.put("remove_by_id", new RemoveByIdCommand(collection, printer));
        supplierMap.put("remove_by_id",
                () -> new Object[]{requestElement.get("Enter id:", sc, printer, inputData::getId, true)});
        commandsByName.put("clear", new ClearCommand(collection, printer));
        supplierMap.put("clear",
                () -> new Object[]{});
        commandsByName.put("save", new SaveCommand(collection, printer));
        supplierMap.put("save",
                () -> new Object[]{new FileManager(requestElement
                        .get("Enter the file name:", sc, printer, inputData::getFileName, false), printer)
                        .getJsonFileByName()});
        commandsByName.put("execute_script", new ExecuteScriptCommand());
        supplierMap.put("execute_script",
                () -> new Object[]{new AdvancedScript(
                        new FileManager(requestElement
                                .get("Enter the name of the script file:", sc, printer, inputData::getFileName, false), printer)
                                .getTextFileByName(), commandsByName, supplierMap, requestMap, printer)});
        commandsByName.put("add_if_max", new AddIfMaxCommand(collection, printer));
        supplierMap.put("add_if_max",
                () -> new Object[]{new RequestDragon(requestElement, sc, printer, inputData).get().build()});
        commandsByName.put("remove_greater", new RemoveGreaterCommand(collection, printer));
        supplierMap.put("remove_greater",
                () -> new Object[]{new RequestDragon(requestElement, sc, printer, inputData).get().build()});
        commandsByName.put("remove_lower", new RemoveLowerCommand(collection, printer));
        supplierMap.put("remove_lower",
                () -> new Object[]{new RequestDragon(requestElement, sc, printer, inputData).get().build()});
        commandsByName.put("remove_all_by_weight", new RemoveAllByWeight(collection, printer));
        supplierMap.put("remove_all_by_weight",
                () -> new Object[]{requestElement.get("Enter the weight:", sc, printer, inputData::getWeight, true)});
        commandsByName.put("count_greater_than_killer", new CountGreaterThanKillerCommand(collection, printer));
        supplierMap.put("count_greater_than_killer",
                () -> new Object[]{new RequestPerson(requestElement, sc, printer, inputData).get()});
        commandsByName.put("filter_greater_than_age", new FilterGreaterThanAgeCommand(collection, printer));
        supplierMap.put("filter_greater_than_age",
                () -> new Object[]{requestElement.get("Enter the age:", sc, printer, inputData::getAge, true)});
        requestMap.put(Dragon.class, (Scanner scanner, Printer printer) -> new RequestDragon(requestElement, scanner, printer, inputData).get().build());
        requestMap.put(Person.class, (Scanner scanner, Printer printer) -> new RequestPerson(requestElement, scanner, printer, inputData).get().build());
        requestMap.put(Coordinates.class, (Scanner scanner, Printer printer) -> new RequestCoordinates(requestElement, scanner, printer, inputData).get().build());
        requestMap.put(Location.class, (Scanner scanner, Printer printer) -> new RequestLocation(requestElement, scanner, printer, inputData).get().build());
    }
}