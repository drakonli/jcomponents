package drakonli.jcomponents.file;

import drakonli.jcomponents.file.impl.ByNameFileFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ByNameFileFactoryTest
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void create() throws IOException
    {
        File newFolder = this.folder.newFolder();

        ByNameFileFactory factory = new ByNameFileFactory(newFolder);

        File actualFile = factory.create("someName.txt");

        File expectedFile = new File(newFolder, "someName.txt");

        assertEquals(expectedFile, actualFile);
    }
}