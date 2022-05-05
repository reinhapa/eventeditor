package io.hackergarten.eventeditor;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;

@MockitoSettings
class ErrorDialogTest {
    @Mock
    Alert alert;
    @Mock
    DialogPane dialogPane;

    @Test
    void testShowAndWait() {
        /*
        new MockUp<Label>() {
            @Mock
            public void $init(String text) {
                assertEquals("The exception stacktrace was:", text);
            }
        };
        new Expectations() {
            {
                alert.setTitle("Exception Dialog");
                alert.setHeaderText("header");
                alert.setContentText("messageText");
                alert.getDialogPane();
                result = dialogPane;
                alert.showAndWait();
            }
        };
        */

        Exception cause = new Exception("some error");

        try (MockedStatic<Application> applicationStatic = mockStatic(Application.class);
             MockedConstruction<Label> labelConstruction = mockConstruction(Label.class);
             MockedConstruction<Alert> alertConstruction = mockConstruction(Alert.class)) {
            applicationStatic.when(Application::getUserAgentStylesheet).thenReturn("");

            ErrorDialog.showAndWait(cause, "header", "messageText");

            assertThat(alertConstruction.constructed()).hasSize(1);
        }
    }

}
