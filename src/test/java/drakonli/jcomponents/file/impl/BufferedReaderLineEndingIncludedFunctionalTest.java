package drakonli.jcomponents.file.impl;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BufferedReaderLineEndingIncludedFunctionalTest
{
    @Test
    public void readLineTest() throws IOException
    {
        File file = new File("src/test/resources/file_for_buffered_reader_line_ending_included_test.txt");
        BufferedReaderLineEndingIncluded testedReader = new BufferedReaderLineEndingIncluded(
                new InputStreamReader(
                        new FileInputStream(file)
                )
        );

        assertEquals(testedReader.readLine(), "some text".concat(System.lineSeparator()));
        assertEquals(testedReader.readLine(), "some more text".concat(System.lineSeparator()));
        assertTrue(testedReader.readLine().isEmpty());
        assertEquals(testedReader.readLine(), "some more more more text".concat(System.lineSeparator()));
    }
}