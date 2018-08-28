package drakonli.jcomponents.file.manager;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

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
     * the given prefix and suffix to generate its name. The resulting {@code
     * Path} is associated with the default {@code FileSystem}.
     */
    public Path createTempFile(
            String prefix,
            String suffix,
            FileAttribute<?>... attrs
    ) throws IOException;
}
