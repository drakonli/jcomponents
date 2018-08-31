package drakonli.jcomponents.file.writer.factory;

import drakonli.jcomponents.file.impl.BufferedCharsetFileWriterFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedCharsetFileWriterFactoryTest
{
    @Test
    @Ignore("Skipping test. Can not find a way to test equality of resulting writers")
    public void createWriter() throws IOException
    {

        File file = new File("someName.txt");
        Charset charset = java.nio.charset.StandardCharsets.UTF_16LE;

        BufferedWriter expectedWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));

        Writer actualWriter = (new BufferedCharsetFileWriterFactory(charset)).createWriter(file);

        Assert.assertThat(expectedWriter, new ReflectionEquals(actualWriter));
    }
}