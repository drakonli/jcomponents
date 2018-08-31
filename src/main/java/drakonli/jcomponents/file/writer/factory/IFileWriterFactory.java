package drakonli.jcomponents.file.writer.factory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public interface IFileWriterFactory
{
    Writer createWriter(File file) throws IOException;
}
