package drakonli.jcomponents.file.writer.factory;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedCharsetFileWriterFactory implements FileWriterFactoryInterface
{
    final private Charset charset;

    public BufferedCharsetFileWriterFactory(Charset charset)
    {
        this.charset = charset;
    }

    @Override
    public Writer createWriter(File file) throws IOException
    {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), this.charset));
    }
}
