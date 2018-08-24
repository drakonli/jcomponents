package drakonli.jcomponents.file.backuper;

import java.io.File;
import java.io.IOException;

public interface IFileBackuper
{
    void backup(File file) throws IOException;
}
