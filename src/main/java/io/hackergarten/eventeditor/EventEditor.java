package io.hackergarten.eventeditor;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main editor application.
 *
 * @author Patrick Reinhart
 */
public class EventEditor extends Application {
  private final Preferences preferences;

  public EventEditor() {
    preferences = Preferences.userNodeForPackage(getClass());
  }

  @Override
  public void start(Stage stage) throws Exception {
    Class<? extends EventEditor> adminClass = getClass();
    ResourceBundle rb = ResourceBundle.getBundle(adminClass.getCanonicalName());
    URL url = adminClass.getResource("EventEditor.fxml");
    FXMLLoader loader = new FXMLLoader(url, rb);
    Parent root = loader.load();
    EventEditorController controller = loader.getController();
    controller.setMainApp(this);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(adminClass.getResource("EventEditor.css").toExternalForm());
    stage.setScene(scene);
    stage.show();
  }

  /**
   * @return the editor preferences
   */
  Preferences getPreferences() {
    return preferences;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}
