package drakonli.jcomponents.file.reader.buffered.charset;

import drakonli.jcomponents.file.impl.BufferedCharsetLineEndingIncludedFileReaderFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedCharsetLineEndingIncludedFileReaderFactoryTest
{
    @Test
    @Ignore("Skipping test. Can not find a way to test equality of resulting readers")
    public void createFileReader() throws IOException
    {
        File file = new File("someName.txt");
        Charset charset = java.nio.charset.StandardCharsets.UTF_16LE;

        BufferedReader expectedReader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file),
                                charset
                        )
                );

        BufferedReader actualReader = (new BufferedCharsetLineEndingIncludedFileReaderFactory(charset))
                .createFileReader(file);

        Assert.assertThat(expectedReader, new ReflectionEquals(actualReader));
    }
}