package drakonli.jcomponents.notificator;

public interface NotificatorInterface
{
    void success(String message);

    void error(String message);

    void info(String message);
}
