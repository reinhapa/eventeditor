package io.hackergarten.eventeditor;

import java.net.URL;
import java.util.ResourceBundle;

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
  @Override
  public void start(Stage stage) throws Exception {
    Class<? extends EventEditor> adminClass = getClass();
    ResourceBundle rb = ResourceBundle.getBundle(adminClass.getCanonicalName());
    URL url = adminClass.getResource("EventEditor.fxml");
    FXMLLoader loader = new FXMLLoader(url, rb);
    Parent root = loader.load();
    EventEditorController controller = loader.getController();

    Scene scene = new Scene(root);
    scene.getStylesheets().add(adminClass.getResource("EventEditor.css").toExternalForm());
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() throws Exception {
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}
