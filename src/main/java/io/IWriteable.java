package io;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IWriteable {
    void write(String string) throws IOException;
}
