package io.hackergarten.eventeditor;

import static org.junit.Assert.assertEquals;

import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;

public class EventEditorTest {
  private EventEditor editor;

  @Before
  public void setUp() {
    editor = new EventEditor();
  }

  @Test
  public void testGetPreferences() {
    Preferences prefs = editor.getPreferences();
    assertEquals("/io/hackergarten/eventeditor", prefs.absolutePath());
  }

  @Test
  public void testStart(@Injectable Stage stage, @Injectable Parent root,
      @Injectable EventEditorController controller, @Mocked FXMLLoader loader, @Mocked Scene scene)
      throws Exception {
    final ObservableList<String> stylesheets = FXCollections.observableArrayList();

    new Expectations() {
      {
        loader.load();
        result = root;
        loader.getController();
        result = controller;
        scene.getStylesheets();
        result = stylesheets;
      }
    };
    editor.start(stage);

    assertEquals(1, stylesheets.size());
  }

  @Test
  public void testMain(@Mocked Application application) {
    String[] args = {"a", "b", "c"};

    new Expectations() {
      {
        Application.launch(args);
        times = 1;
      }
    };

    EventEditor.main(args);
  }
}
