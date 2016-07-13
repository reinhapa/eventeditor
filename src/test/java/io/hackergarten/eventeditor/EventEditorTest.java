package io.hackergarten.eventeditor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class EventEditorTest {
  private EventEditor editor;

  @Before
  public void setUp() {
    editor = new EventEditor();
  }

  @Test
  public void testStartStage(@Injectable Stage stage, @Injectable Parent root,
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
}
