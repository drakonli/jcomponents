package drakonli.jcomponents.notificator;

import javafx.scene.control.Alert;

public class AlertNotificator implements NotificatorInterface
{
    public void success(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Everything's fine, bub");
        alert.setHeaderText(message);

        alert.showAndWait();
    }

    public void error(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bad feeling I have about this");
        alert.setHeaderText(message);

        alert.showAndWait();
    }

    public void info(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Here's some info for you");
        alert.setHeaderText(message);

        alert.showAndWait();
    }
}
