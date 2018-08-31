package drakonli.jcomponents.file.backuper;

import drakonli.jcomponents.file.impl.ByNameFileFactory;
import drakonli.jcomponents.file.impl.DoNotOverwriteFileSystemFileBackuper;
import drakonli.jcomponents.file.impl.NioFileManager;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoNotOverwriteFileSystemFileBackuperFunctionalTest
{
    private DoNotOverwriteFileSystemFileBackuper backuper;

    @Before
    public void setUp()
    {
        this.backuper = new DoNotOverwriteFileSystemFileBackuper(
                new ByNameFileFactory(),
                new NioFileManager("tmp_file", ".txt")
        );
    }

    @Test
    public void testBackup() throws IOException
    {
        File testFile = new File("src/test/resources/file_for_test_backup.txt");
        File backupFile = new File(testFile.getName());

        this.backuper.backup(testFile);

        assertTrue("File was not backed-up properly", backupFile.isFile());

        backupFile.deleteOnExit();
    }

    @Test
    public void testBackupWithoutOverwrite() throws IOException
    {
        File testFile = new File("src/test/resources/file_for_test_backup.txt");

        File backupFile = new File(testFile.getName());
        Files.copy(testFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        File overwriteFile = new File("src/test/resources/overwrite/file_for_test_backup.txt");

        this.backuper.backup(overwriteFile);

        assertFalse("Backuper should not overwrite a file", FileUtils.contentEquals(overwriteFile, backupFile));

        backupFile.deleteOnExit();
    }
}