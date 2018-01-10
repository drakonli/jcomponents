package drakonli.jcomponents.file.backuper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileBackuper
{
    public void backupOriginal(File file) throws IOException
    {
        File backupFile = new File(file.getName());

        if (backupFile.isFile()) {
            return;
        }

        Files.copy(file.toPath(), backupFile.toPath(), REPLACE_EXISTING);
    }
}
