package drakonli.jcomponents.file.reader.buffered.charset.segment;

import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

import java.io.*;
import java.nio.charset.Charset;

public class FileSegmentBufferedCharsetReaderFactory implements BufferedFileReaderFactoryInterface
{
    final private TxtLinePredicateInterface skipFromLinePredicate;
    final private TxtLinePredicateInterface skipToLinePredicate;
    final private Charset charset;

    public FileSegmentBufferedCharsetReaderFactory(
            TxtLinePredicateInterface skipFromLinePredicate,
            TxtLinePredicateInterface skipToLinePredicate,
            Charset charset
    )
    {
        this.skipFromLinePredicate = skipFromLinePredicate;
        this.skipToLinePredicate = skipToLinePredicate;
        this.charset = charset;
    }

    @Override
    public BufferedReader createFileReader(File file) throws IOException
    {
        return new FileSegmentBufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        this.charset
                ),
                this.skipFromLinePredicate,
                this.skipToLinePredicate
        );
    }
}
