package drakonli.jcomponents.file.impl;

import drakonli.jcomponents.file.IFileChooserFactory;
import javafx.stage.FileChooser;

import java.io.File;

public class GuessedDirectoryWithAllowedExtensionsFileChooserFactory implements IFileChooserFactory
{
    private final String[] initialDirectoryGuesses;
    private final String[] allowedExtensions;
    private final String allowedExtensionsDescription;
    private final String title;

    public GuessedDirectoryWithAllowedExtensionsFileChooserFactory(
            String[] initialDirectoryGuesses,
            String[] allowedExtensions,
            String allowedExtensionsDescription,
            String title
    )
    {
        this.initialDirectoryGuesses = initialDirectoryGuesses;
        this.allowedExtensions = allowedExtensions;
        this.allowedExtensionsDescription = allowedExtensionsDescription;
        this.title = title;
    }

    @Override
    public FileChooser createFileChooser()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(this.title);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                this.allowedExtensionsDescription,
                this.allowedExtensions
        );
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(this.guessInitialDirectory());

        return fileChooser;
    }

    private File guessInitialDirectory()
    {
        for (String initialDirectoryPath : this.initialDirectoryGuesses) {
            File initialDirectory = new File(initialDirectoryPath);

            if (!initialDirectory.isDirectory()) {
                continue;
            }

            return initialDirectory;
        }

        return null;
    }
}
