package drakonli.jcomponents.file.editor.txt.tmp;

import drakonli.jcomponents.file.editor.txt.ITxtFileByLineEditor;
import drakonli.jcomponents.file.editor.txt.ITxtLineEditor;
import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.file.manager.IFileManager;
import drakonli.jcomponents.file.reader.buffered.IBufferedFileReaderFactory;
import drakonli.jcomponents.file.writer.factory.IFileWriterFactory;
import drakonli.jcomponents.predicate.ITxtLinePredicate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.StandardCopyOption;

public class TmpTxtFileByLineEditor implements ITxtFileByLineEditor
{
    private final IBufferedFileReaderFactory bufferedReaderFactory;
    private final IFileWriterFactory         fileWriterFactory;
    private final IFileManager               fileManager;

    public TmpTxtFileByLineEditor(
            IBufferedFileReaderFactory bufferedReaderFactory,
            IFileWriterFactory fileWriterFactory,
            IFileManager fileManager
    )
    {
        this.bufferedReaderFactory = bufferedReaderFactory;
        this.fileWriterFactory = fileWriterFactory;
        this.fileManager = fileManager;
    }

    public void edit(File file, ITxtLineEditor lineEditor, ITxtLinePredicate lineToEditPredicate)
            throws IOException, NoLineQualifiedForEditException
    {
        File tmpFile = this.fileManager.createTempFile();

        BufferedReader fileReader = this.bufferedReaderFactory.createFileReader(file);
        Writer writer = this.fileWriterFactory.createWriter(tmpFile);

        Boolean fileIsQualified = false;

        String currentLine;
        while (null != (currentLine = fileReader.readLine())) {
            currentLine = currentLine.concat(System.lineSeparator());

            if (lineToEditPredicate.test(currentLine)) {
                fileIsQualified = true;

                currentLine = lineEditor.editLine(currentLine);
            }

            writer.write(currentLine);
        }

        fileReader.close();
        writer.close();

        if (!fileIsQualified) {
            throw new NoLineQualifiedForEditException();
        }

        this.fileManager.move(tmpFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
