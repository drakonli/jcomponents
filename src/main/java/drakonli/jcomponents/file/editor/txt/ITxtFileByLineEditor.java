package drakonli.jcomponents.file.editor.txt;

import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.predicate.ITxtLinePredicate;

import java.io.File;
import java.io.IOException;

public interface ITxtFileByLineEditor
{
    void edit(File file, ITxtLineEditor lineEditor, ITxtLinePredicate lineToEditPredicate)
            throws IOException, NoLineQualifiedForEditException;
}
