package drakonli.jcomponents.file.chooser;

import javafx.stage.FileChooser;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuessedDirectoryTxtFileChooserFactoryTest
{

    @Test
    public void createFileChooser()
    {
        String[] directories = {"somedir2", "."};
        String[] allowedExtensions = {".someext1", ".someext2"};
        String expectedExtDescription = "some descrp";
        String expectedTitle = "some title";
        List<String> expectedAllowedExtension = Collections.unmodifiableList(
                Arrays.asList(allowedExtensions.clone()));

        FileChooser actualFileChooser =
                (new GuessedDirectoryTxtFileChooserFactory(
                        directories,
                        allowedExtensions,
                        expectedExtDescription,
                        expectedTitle
                )).createFileChooser();

        assertTrue(actualFileChooser.getExtensionFilters().size() == 1);

        FileChooser.ExtensionFilter filter = actualFileChooser.getExtensionFilters().get(0);

        assertEquals(expectedTitle, actualFileChooser.getTitle());
        assertEquals(expectedAllowedExtension, filter.getExtensions());
        assertEquals(expectedExtDescription, filter.getDescription());
        assertTrue(actualFileChooser.getInitialDirectory().equals(new File(".")));
    }
}