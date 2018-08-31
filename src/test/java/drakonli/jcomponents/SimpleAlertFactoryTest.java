package drakonli.jcomponents;

import drakonli.jcomponents.impl.SimpleAlertFactory;
import drakonli.util.JavaFXThreadingRule;
import javafx.scene.control.Alert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleAlertFactoryTest
{
    @Rule
    public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

    @Test
    public void createSimpleAlert()
    {
        SimpleAlertFactory simpleAlertFactory = new SimpleAlertFactory();

        Alert.AlertType alertType = Alert.AlertType.INFORMATION;
        String headerText = "someHeaderText";
        String title = "someTitle";

        Alert expectedAlert = new Alert(alertType);
        expectedAlert.setTitle(title);
        expectedAlert.setHeaderText(headerText);

        Alert actualAlert = simpleAlertFactory.createSimpleAlert(title, headerText, alertType);

        assertEquals("Alert type does not equal expected", expectedAlert.getAlertType(), actualAlert.getAlertType());
        assertEquals("Alert title does not equal expected", expectedAlert.getTitle(), actualAlert.getTitle());
        assertEquals(
                "Alert header test does not equal expected",
                expectedAlert.getHeaderText(),
                actualAlert.getHeaderText()
        );
    }
}