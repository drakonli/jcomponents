package drakonli.jcomponents.file.reader.buffered.charset.segment;

import java.io.IOException;

public class InvalidSkipToPredicateException extends IOException
{
    public InvalidSkipToPredicateException()
    {
        super("Line to skip to was not found in file");
    }
}
