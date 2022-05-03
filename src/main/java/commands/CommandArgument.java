package commands;


import java.util.Map;
import java.util.function.Supplier;


public class CommandArgument {
    private final String commandAsString;
    private final Map<String, Supplier<Object[]>> stringSupplierMap;

    public CommandArgument(String commandAsString, Map<String, Supplier<Object[]>> stringSupplierMap) {
        this.commandAsString = commandAsString;
        this.stringSupplierMap = stringSupplierMap;
    }

    public Object[] get() {
        if (stringSupplierMap.containsKey(commandAsString)) {
            return stringSupplierMap.get(commandAsString).get();
        }
        throw new IllegalArgumentException("Ваши данные некорректны");
    }
}
