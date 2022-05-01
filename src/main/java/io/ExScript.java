package io;

import commands.Command;
import file.JsonFile;
import file.TextFile;

import java.io.*;
import java.util.Map;

public class ExScript {
    private final TextFile textFile;
    private final Map<String, Command> commandMap;

    public ExScript(TextFile textFile, Map<String, Command> commandMap) {
        this.textFile = textFile;
        this.commandMap = commandMap;
    }

    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(textFile)));
        while (reader.ready()) {
            String commandString = reader.readLine();
            if (commandMap.containsKey(commandString)) {

            }
        }
    }
}

