package drakonli.jcomponents.file.impl;

import drakonli.jcomponents.file.IByNameFileFactory;

import java.io.File;

public class ByNameFileFactory implements IByNameFileFactory
{
    private final File parentDirectory;

    public ByNameFileFactory(File directory)
    {
        if (!directory.isDirectory()) {
            throw new RuntimeException("Passed parent File should be a directory");
        }

        this.parentDirectory = directory;
    }

    @Override
    public File create(String name)
    {
        return new File(this.parentDirectory, name);
    }
}
