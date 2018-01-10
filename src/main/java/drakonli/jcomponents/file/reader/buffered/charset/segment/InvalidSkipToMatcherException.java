package drakonli.jcomponents.file.reader.buffered.charset.segment;

import java.io.IOException;

public class InvalidSkipToMatcherException extends IOException
{
    public InvalidSkipToMatcherException()
    {
        super("Line to skip to was not found in file");
    }
}
