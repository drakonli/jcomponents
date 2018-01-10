package drakonli.jcomponents.file.reader.buffered;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public interface BufferedFileReaderFactoryInterface
{
    BufferedReader createFileReader(File file) throws IOException;
}
