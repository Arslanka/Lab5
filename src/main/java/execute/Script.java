package execute;

import file.TextFile;
import io.Console;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Script {
    protected static final Set<File> executableScripts = new HashSet<>();

    private final TextFile textFile;
    private final Console console;

    public Script(TextFile textFile, Console console) {
        this.textFile = textFile;
        this.console = console;
    }

    public boolean execute() throws IOException {
        if (executableScripts.contains(textFile.getFile()))
            throw new IllegalStateException("Вы находитесь в процессе выполнения скрипта " + textFile + ". Ошибка выполнения скрипта");
        executableScripts.add(textFile.getFile());
        try {
            Scanner sc = new Scanner(textFile.read());
            console.setInput(sc);
            console.fillCommands(sc);
            console.startInteractiveMode();
            executableScripts.remove(textFile.getFile());
            return console.wasExit();
        } catch (IOException e) {
            throw new IOException("Не удалось прочитать файл.");
        }
    }

    public static int getExecutableScriptsAmount() {
        return (executableScripts.size() > 0 ? 1 : 0);
    }
}
