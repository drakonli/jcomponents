package drakonli.jcomponents.file.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderLineEndingIncluded extends BufferedReader
{
    public BufferedReaderLineEndingIncluded(Reader in, int sz)
    {
        super(in, sz);
    }

    public BufferedReaderLineEndingIncluded(Reader in)
    {
        super(in);
    }

    @Override
    public String readLine() throws IOException
    {
        String line = super.readLine();

        if (null == line || line.isEmpty()) {
            return line;
        }

        return line.concat(System.lineSeparator());
    }
}
