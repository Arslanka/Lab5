package io;

public class RequestElement {
    private final String commandAsString;

    public RequestElement(String commandAsString) {
        this.commandAsString = commandAsString;
    }

    public boolean read() { //Написать декодирование команд + эксепшны. возвращать false, если exit
        return true;
    }
}
