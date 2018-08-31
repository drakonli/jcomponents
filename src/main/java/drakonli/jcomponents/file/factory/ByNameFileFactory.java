package drakonli.jcomponents.file.factory;

import java.io.File;

public class ByNameFileFactory implements IByNameFileFactory
{
    @Override
    public File create(String name)
    {
        return new File(name);
    }
}
