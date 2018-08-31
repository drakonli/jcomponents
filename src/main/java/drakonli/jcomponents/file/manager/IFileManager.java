package drakonli.jcomponents.file.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Path;

public interface IFileManager
{
    /**
     * Move or rename a file to a target file.
     */
    public Path move(Path source, Path target, CopyOption... options) throws IOException;

    /**
     * Copy a file to a target file.
     */
    public Path copy(Path source, Path target, CopyOption... options) throws IOException;

    /**
     * Tests if two paths locate the same file.
     */
    public boolean isSameFile(Path path, Path path2) throws IOException;

    /**
     * Creates an empty file in the default temporary-file directory, using
     * the given prefix and suffix to generate its name
     */
    public File createTempFile(String prefix, String suffix) throws IOException;

    /**
     * Creates an empty file in the default temporary-file directory. Uses default prefix and suffix.
     */
    public File createTempFile() throws IOException;
}
