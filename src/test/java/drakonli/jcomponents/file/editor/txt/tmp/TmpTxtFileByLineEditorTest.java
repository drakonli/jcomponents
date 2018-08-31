package drakonli.jcomponents.file.editor.txt.tmp;

import drakonli.jcomponents.file.editor.txt.ITxtLineEditor;
import drakonli.jcomponents.file.editor.txt.exception.NoLineQualifiedForEditException;
import drakonli.jcomponents.file.manager.IFileManager;
import drakonli.jcomponents.file.reader.buffered.IBufferedFileReaderFactory;
import drakonli.jcomponents.file.writer.factory.IFileWriterFactory;
import drakonli.jcomponents.predicate.ITxtLinePredicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TmpTxtFileByLineEditorTest
{
    @Mock
    Path                       tmpFilePathMock;
    @Mock
    Path                       filePathMock;
    @Mock
    File                       tmpFileMock;
    @Mock
    File                       fileMock;
    @Mock
    BufferedReader             readerMock;
    @Mock
    IBufferedFileReaderFactory readerFactoryMock;
    @Mock
    Writer                     writerMock;
    @Mock
    IFileWriterFactory         writerFactoryMock;
    @Mock
    IFileManager               fileManagerMock;
    @Mock
    ITxtLinePredicate          predicateMock;
    @Mock
    ITxtLineEditor             editorMock;

    protected TmpTxtFileByLineEditor testedEditor;

    @Before
    public void beforeEachTest() throws IOException
    {
        when(this.fileManagerMock.createTempFile()).thenReturn(this.tmpFileMock);
        when(this.fileMock.toPath()).thenReturn(this.filePathMock);
        when(this.tmpFileMock.toPath()).thenReturn(this.tmpFilePathMock);
        when(this.readerFactoryMock.createFileReader(this.fileMock)).thenReturn(this.readerMock);
        when(this.writerFactoryMock.createWriter(this.tmpFileMock)).thenReturn(this.writerMock);

        this.testedEditor = new TmpTxtFileByLineEditor(
                this.readerFactoryMock,
                this.writerFactoryMock,
                this.fileManagerMock
        );
    }

    @Test
    public void testWithEmptyReader() throws IOException
    {
        try {
            this.testedEditor.edit(this.fileMock, this.editorMock, this.predicateMock);
        } catch (NoLineQualifiedForEditException e) {
            assertEquals("No line was found that qualified for editing", e.getMessage());
        }

        verify(this.readerMock).readLine();
        verify(this.editorMock, never()).editLine(anyString());
        verify(this.predicateMock, never()).test(anyString());
        verify(this.writerMock, never()).write(anyString());

        verify(this.readerMock).close();
        verify(this.writerMock).close();
        verify(this.fileManagerMock, never()).move(any(Path.class), any(Path.class));
    }

    @Test
    public void testEditWithNoLineQualifiedForEdit() throws IOException
    {
        when(this.readerMock.readLine())
                .thenReturn("first string")
                .thenReturn("second string")
                .thenReturn("third string")
                .thenReturn(null);

        when(this.predicateMock.test("first string" + System.lineSeparator())).thenReturn(false);
        when(this.predicateMock.test("second string" + System.lineSeparator())).thenReturn(false);
        when(this.predicateMock.test("third string" + System.lineSeparator())).thenReturn(false);

        try {
            this.testedEditor.edit(this.fileMock, this.editorMock, this.predicateMock);
        } catch (NoLineQualifiedForEditException e) {
            assertEquals("No line was found that qualified for editing", e.getMessage());
        }

        verify(this.editorMock, never()).editLine(anyString());

        verify(this.writerMock, times(3)).write(anyString());
        verify(this.writerMock).write("first string" + System.lineSeparator());
        verify(this.writerMock).write("second string" + System.lineSeparator());
        verify(this.writerMock).write("third string" + System.lineSeparator());

        verify(this.readerMock).close();
        verify(this.writerMock).close();

        verify(this.fileManagerMock, never()).move(any(Path.class), any(Path.class));
    }

    @Test
    public void test() throws IOException, NoLineQualifiedForEditException
    {
        when(this.readerMock.readLine())
                .thenReturn("first string")
                .thenReturn("second string")
                .thenReturn("third string")
                .thenReturn(null);

        when(this.predicateMock.test("first string" + System.lineSeparator())).thenReturn(true);
        when(this.predicateMock.test("second string" + System.lineSeparator())).thenReturn(false);
        when(this.predicateMock.test("third string" + System.lineSeparator())).thenReturn(true);

        when(this.editorMock.editLine("first string" + System.lineSeparator())).thenReturn("result first line");
        when(this.editorMock.editLine("third string" + System.lineSeparator())).thenReturn("result third line");

        this.testedEditor.edit(this.fileMock, this.editorMock, this.predicateMock);

        verify(this.editorMock, never()).editLine("second string" + System.lineSeparator());

        verify(this.writerMock, times(3)).write(anyString());
        verify(this.writerMock).write("result first line");
        verify(this.writerMock).write("second string" + System.lineSeparator());
        verify(this.writerMock).write("result third line");

        verify(this.readerMock).close();
        verify(this.writerMock).close();

        verify(this.fileManagerMock).move(this.tmpFilePathMock, this.filePathMock, StandardCopyOption.REPLACE_EXISTING);
    }
}