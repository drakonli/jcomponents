package drakonli.jcomponents.file.backuper;

import drakonli.jcomponents.file.factory.IByNameFileFactory;
import drakonli.jcomponents.file.manager.IFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

public class DoNotOverwriteFileSystemFileBackuper implements IFileBackuper
{
    private final IByNameFileFactory fileFactory;
    private final IFileManager       fileManager;

    public DoNotOverwriteFileSystemFileBackuper(
            IByNameFileFactory fileFactory,
            IFileManager fileManager
    )
    {
        this.fileFactory = fileFactory;
        this.fileManager = fileManager;
    }

    @Override
    public void backup(File file) throws IOException
    {
        File backupFile = this.fileFactory.create(file.getName());

        if (backupFile.isFile()) {
            return;
        }

        this.fileManager.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
