package drakonli.jcomponents.file.editor.txt.tmp;

import drakonli.jcomponents.file.editor.txt.TxtFileByLineEditorInterface;
import drakonli.jcomponents.file.editor.txt.TxtLineEditorInterface;
import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.jcomponents.file.writer.factory.FileWriterFactoryInterface;
import drakonli.jcomponents.predicate.TxtLinePredicateInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TmpTxtFileByLineEditor implements TxtFileByLineEditorInterface
{
    private final String tmpFilePrefix;
    private final String tmpFileSuffix;
    private final BufferedFileReaderFactoryInterface bufferedReaderFactory;
    private final FileWriterFactoryInterface fileWriterFactory;

    public TmpTxtFileByLineEditor(
            String tmpFilePrefix,
            String tmpFileSuffix,
            BufferedFileReaderFactoryInterface bufferedReaderFactory,
            FileWriterFactoryInterface fileWriterFactory
    )
    {
        this.tmpFilePrefix = tmpFilePrefix;
        this.tmpFileSuffix = tmpFileSuffix;
        this.bufferedReaderFactory = bufferedReaderFactory;
        this.fileWriterFactory = fileWriterFactory;
    }

    public void edit(File file, TxtLineEditorInterface lineEditor, TxtLinePredicateInterface lineToEditPredicate)
            throws IOException, NoLineQualifiedForEditException
    {
        File tmpFile = File.createTempFile(
                this.tmpFilePrefix,
                this.tmpFileSuffix
        );

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

        Files.move(tmpFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
