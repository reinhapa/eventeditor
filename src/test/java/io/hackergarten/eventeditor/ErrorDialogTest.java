package io.hackergarten.eventeditor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class ErrorDialogTest {
  @Mocked
  Alert alert;
  @Injectable
  DialogPane dialogPane;

  @Test
  public void testShowAndWait() {
    new MockUp<Application>() {
      @Mock
      public String getUserAgentStylesheet() {
        return "";
      }
    };
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
    Exception cause = new Exception("some error");

    ErrorDialog.showAndWait(cause, "header", "messageText");
  }

}
