package drakonli.jcomponents.file;

import drakonli.jcomponents.ITxtLineEditor;
import drakonli.jcomponents.ITxtLinePredicate;
import drakonli.jcomponents.exception.NoLineQualifiedForEditException;

import java.io.File;
import java.io.IOException;

public interface ITxtFileByLineEditor
{
    void edit(File file, ITxtLineEditor lineEditor, ITxtLinePredicate lineToEditPredicate)
            throws IOException, NoLineQualifiedForEditException;
}
