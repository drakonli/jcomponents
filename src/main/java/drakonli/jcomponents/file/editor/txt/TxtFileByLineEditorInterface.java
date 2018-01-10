package drakonli.jcomponents.file.editor.txt;

import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

import java.io.File;
import java.io.IOException;

public interface TxtFileByLineEditorInterface
{
    void edit(File file, TxtLineEditorInterface lineEditor, TxtLinePredicateInterface lineToEditPredicate)
            throws IOException, NoLineQualifiedForEditException;
}
