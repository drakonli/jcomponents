package drakonli.jcomponents.file.reader.buffered.charset.segment;

import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

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
    private final TxtLinePredicateInterface skipToLinePredicate;
    private final TxtLinePredicateInterface skipFromLinePredicate;

    private Boolean startSkipped = false;

    public FileSegmentBufferedReader(
            Reader in, int sz,
            TxtLinePredicateInterface skipFromLinePredicate,
            TxtLinePredicateInterface skipToLinePredicate
    ) throws IOException
    {
        super(in, sz);
        this.skipFromLinePredicate = skipFromLinePredicate;
        this.skipToLinePredicate = skipToLinePredicate;

        this.skipStart();
    }

    public FileSegmentBufferedReader(
            Reader in,
            TxtLinePredicateInterface skipFromLinePredicate,
            TxtLinePredicateInterface skipToLinePredicate
    ) throws IOException
    {
        super(in);
        this.skipFromLinePredicate = skipFromLinePredicate;
        this.skipToLinePredicate = skipToLinePredicate;

        this.skipStart();
    }

    private void skipStart() throws IOException
    {
        String currentLine;
        while (null != (currentLine = this.readLine())) {
            if (this.skipFromLinePredicate.test(currentLine)) {
                this.startSkipped = true;

                return;
            }
        }

        throw new InvalidSkipToPredicateException();
    }

    @Override
    public String readLine() throws IOException
    {
        String currentLine = super.readLine();

        if (this.startSkipped && null != currentLine && this.skipToLinePredicate.test(currentLine)) {
            return null;
        }

        return currentLine;
    }
}
