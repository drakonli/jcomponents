package drakonli.jcomponents.file.reader.buffered.charset.segment;

import drakonli.jcomponents.matcher.MatcherInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * BufferedReader that skips reading lines to a certain matched line (fromLine) and reads until
 * another matched line (toLine). This is to optimize reading the file - when you don't really need to work with all
 * contents of the file, but only with a segment
 */
public class FileSegmentBufferedReader extends BufferedReader
{
    private final MatcherInterface<String> skipToLineMatcher;
    private final MatcherInterface<String> skipFromLineMatcher;

    private Boolean startSkipped = false;

    public FileSegmentBufferedReader(
            Reader in, int sz,
            MatcherInterface<String> skipFromLineMatcher,
            MatcherInterface<String> skipToLineMatcher
    ) throws IOException
    {
        super(in, sz);
        this.skipFromLineMatcher = skipFromLineMatcher;
        this.skipToLineMatcher = skipToLineMatcher;

        this.skipStart();
    }

    public FileSegmentBufferedReader(
            Reader in,
            MatcherInterface<String> skipFromLineMatcher,
            MatcherInterface<String> skipToLineMatcher
    ) throws IOException
    {
        super(in);
        this.skipFromLineMatcher = skipFromLineMatcher;
        this.skipToLineMatcher = skipToLineMatcher;

        this.skipStart();
    }

    private void skipStart() throws IOException
    {
        String currentLine;
        while (null != (currentLine = this.readLine())) {
            if (this.skipFromLineMatcher.match(currentLine)) {
                this.startSkipped = true;

                return;
            }
        }

        throw new InvalidSkipToMatcherException();
    }

    @Override
    public String readLine() throws IOException
    {
        String currentLine = super.readLine();

        if (this.startSkipped && null != currentLine && this.skipToLineMatcher.match(currentLine)) {
            return null;
        }

        return currentLine;
    }
}
