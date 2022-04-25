package io;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Writeable {
    void write(String string) throws IOException;
}
