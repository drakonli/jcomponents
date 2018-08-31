package drakonli.jcomponents.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public interface IBufferedFileReaderFactory
{
    BufferedReader createFileReader(File file) throws IOException;
}
