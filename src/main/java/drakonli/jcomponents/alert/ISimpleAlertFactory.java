package drakonli.jcomponents.alert;

import javafx.scene.control.Alert;

public interface ISimpleAlertFactory
{
    public Alert createSimpleAlert(String title, String headerText, Alert.AlertType alertType);
}
