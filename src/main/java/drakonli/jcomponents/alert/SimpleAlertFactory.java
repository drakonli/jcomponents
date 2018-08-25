package drakonli.jcomponents.alert;

import javafx.scene.control.Alert;

public class SimpleAlertFactory implements ISimpleAlertFactory
{
    @Override
    public Alert createSimpleAlert(String title, String headerText, Alert.AlertType alertType)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);

        return alert;
    }
}
