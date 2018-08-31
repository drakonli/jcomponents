package drakonli.jcomponents.file.impl;

import drakonli.jcomponents.file.IBufferedFileReaderFactory;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedCharsetLineEndingIncludedFileReaderFactory implements IBufferedFileReaderFactory
{
    final private Charset charset;

    public BufferedCharsetLineEndingIncludedFileReaderFactory(Charset charset)
    {
        this.charset = charset;
    }

    @Override
    public BufferedReader createFileReader(File file) throws IOException
    {
        return new BufferedReaderLineEndingIncluded(
                new InputStreamReader(
                        new FileInputStream(file),
                        this.charset
                )
        );
    }
}
