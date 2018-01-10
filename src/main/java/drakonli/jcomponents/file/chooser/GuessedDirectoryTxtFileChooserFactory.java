package drakonli.jcomponents.file.chooser;

import javafx.stage.FileChooser;

import java.io.File;

public class GuessedDirectoryTxtFileChooserFactory implements FileChooserFactoryInterface
{
    private final String[] initialDirectoryGuesses;

    public GuessedDirectoryTxtFileChooserFactory(String[] initialDirectoryGuesses)
    {
        this.initialDirectoryGuesses = initialDirectoryGuesses;
    }

    @Override
    public FileChooser createFileChooser()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
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
