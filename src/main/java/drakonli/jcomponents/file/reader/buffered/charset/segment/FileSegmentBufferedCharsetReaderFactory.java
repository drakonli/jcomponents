package drakonli.jcomponents.file.reader.buffered.charset.segment;

import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.jcomponents.matcher.MatcherInterface;

import java.io.*;
import java.nio.charset.Charset;

public class FileSegmentBufferedCharsetReaderFactory implements BufferedFileReaderFactoryInterface
{
    final private MatcherInterface<String> skipFromLineMatcher;
    final private MatcherInterface<String> skipToLineMatcher;
    final private Charset charset;

    public FileSegmentBufferedCharsetReaderFactory(
            MatcherInterface<String> skipFromLineMatcher,
            MatcherInterface<String> skipToLineMatcher,
            Charset charset
    )
    {
        this.skipFromLineMatcher = skipFromLineMatcher;
        this.skipToLineMatcher = skipToLineMatcher;
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
                this.skipFromLineMatcher,
                this.skipToLineMatcher
        );
    }
}
