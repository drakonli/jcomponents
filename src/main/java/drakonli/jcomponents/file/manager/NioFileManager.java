package drakonli.jcomponents.file.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioFileManager implements IFileManager
{
    private final String tmpFilePrefix;
    private final String tmpFileSuffix;

    public NioFileManager(String tmpFilePrefix, String tmpFileSuffix)
    {
        this.tmpFilePrefix = tmpFilePrefix;
        this.tmpFileSuffix = tmpFileSuffix;
    }

    @Override
    public Path move(Path source, Path target, CopyOption... options) throws IOException
    {
        return Files.move(source, target, options);
    }

    @Override
    public Path copy(Path source, Path target, CopyOption... options) throws IOException
    {
        return Files.copy(source, target, options);
    }

    @Override
    public boolean isSameFile(Path path, Path path2) throws IOException
    {
        return Files.isSameFile(path, path2);
    }

    @Override
    public File createTempFile(String prefix, String suffix) throws IOException
    {
        return File.createTempFile(prefix, suffix);
    }

    @Override
    public File createTempFile() throws IOException
    {
        return this.createTempFile(this.tmpFilePrefix, this.tmpFileSuffix);
    }
}
