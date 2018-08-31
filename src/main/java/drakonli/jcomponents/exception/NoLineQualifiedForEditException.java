package drakonli.jcomponents.exception;

public class NoLineQualifiedForEditException extends Exception
{
    public NoLineQualifiedForEditException()
    {
        super("No line was found that qualified for editing");
    }
}
