package drakonli.jcomponents.impl;

import drakonli.jcomponents.INotificator;
import drakonli.jcomponents.ISimpleAlertFactory;
import javafx.scene.control.Alert;

public class AlertNotificator implements INotificator
{
    private final ISimpleAlertFactory simpleAlertFactory;

    public AlertNotificator(ISimpleAlertFactory simpleAlertFactory)
    {
        this.simpleAlertFactory = simpleAlertFactory;
    }

    public void success(String message)
    {
        Alert alert = this.simpleAlertFactory.createSimpleAlert(
                "Everything's fine, bub",
                message,
                Alert.AlertType.INFORMATION
        );

        alert.showAndWait();
    }

    public void error(String message)
    {
        Alert alert = this.simpleAlertFactory.createSimpleAlert(
                "Bad feeling I have about this",
                message,
                Alert.AlertType.ERROR
        );

        alert.showAndWait();
    }

    public void info(String message)
    {
        Alert alert = this.simpleAlertFactory.createSimpleAlert(
                "Here's some info for you",
                message,
                Alert.AlertType.ERROR
        );

        alert.showAndWait();
    }
}
