package drakonli.jcomponents.notificator;

public interface INotificator
{
    void success(String message);

    void error(String message);

    void info(String message);
}
