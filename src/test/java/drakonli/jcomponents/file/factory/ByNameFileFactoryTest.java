package drakonli.jcomponents.file.factory;

import drakonli.jcomponents.file.impl.ByNameFileFactory;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ByNameFileFactoryTest
{
    @Test
    public void create()
    {
        ByNameFileFactory factory = new ByNameFileFactory();

        File file = factory.create("someName.txt");

        assertEquals(file, new File("someName.txt"));
    }
}