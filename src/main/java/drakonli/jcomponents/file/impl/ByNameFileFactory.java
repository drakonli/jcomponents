package drakonli.jcomponents.file.impl;

import drakonli.jcomponents.file.IByNameFileFactory;

import java.io.File;

public class ByNameFileFactory implements IByNameFileFactory
{
    @Override
    public File create(String name)
    {
        return new File(name);
    }
}
