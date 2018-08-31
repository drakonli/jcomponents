package drakonli.jcomponents.file.impl;

import drakonli.jcomponents.file.IBufferedFileReaderFactory;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedCharsetFileReaderFactory implements IBufferedFileReaderFactory
{
    final private Charset charset;

    public BufferedCharsetFileReaderFactory(Charset charset)
    {
        this.charset = charset;
    }

    @Override
    public BufferedReader createFileReader(File file) throws IOException
    {
        return new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        this.charset
                )
        );
    }
}
