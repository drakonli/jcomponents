package drakonli.jcomponents.file.backuper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileBackuper
{
    public void backup(File file) throws IOException
    {
        File backupFile = new File(file.getName());

        if (backupFile.isFile()) {
            return;
        }

        Files.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
