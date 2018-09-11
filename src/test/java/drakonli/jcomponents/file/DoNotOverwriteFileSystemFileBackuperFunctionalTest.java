package drakonli.jcomponents.file;

import drakonli.jcomponents.file.impl.DoNotOverwriteFileSystemFileBackuper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoNotOverwriteFileSystemFileBackuperFunctionalTest
{
    @Mock
    IByNameFileFactory fileFactory;
    @Mock
    IFileManager       fileManager;
    @Mock
    File               initialFileMock;
    @Mock
    File               backupFileMock;
    @Mock
    Path               initialFilePathMock;
    @Mock
    Path               backupFilePathMock;

    String fileName = "somefile.txt";

    protected DoNotOverwriteFileSystemFileBackuper testedBackuper;

    @Before
    public void setUp()
    {
        this.testedBackuper = new DoNotOverwriteFileSystemFileBackuper(
                this.fileFactory,
                this.fileManager
        );
    }

    @Test
    public void testBackup() throws IOException
    {
        when(this.initialFileMock.getName()).thenReturn(this.fileName);
        when(this.initialFileMock.toPath()).thenReturn(this.initialFilePathMock);
        when(this.fileFactory.create(this.fileName)).thenReturn(this.backupFileMock);
        when(this.backupFileMock.isFile()).thenReturn(false);
        when(this.backupFileMock.toPath()).thenReturn(this.backupFilePathMock);

        this.testedBackuper.backup(this.initialFileMock);

        verify(this.fileManager).copy(
                this.initialFilePathMock,
                this.backupFilePathMock,
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    @Test
    public void testBackupWithoutOverwrite() throws IOException
    {
        when(this.initialFileMock.getName()).thenReturn(this.fileName);
        when(this.fileFactory.create(this.fileName)).thenReturn(this.backupFileMock);
        when(this.backupFileMock.isFile()).thenReturn(true);

        this.testedBackuper.backup(this.initialFileMock);

        verify(this.fileManager, never()).copy(any(), any(), any());
    }
}