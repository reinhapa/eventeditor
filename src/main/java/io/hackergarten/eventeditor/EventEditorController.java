package io.hackergarten.eventeditor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import io.hackergarten.eventeditor.model.Event;
import io.hackergarten.eventeditor.model.EventList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;

/**
 * @author Patrick Reinhart
 */
public class EventEditorController implements Initializable {
  @FXML
  private AnchorPane anchorPane;

  @FXML
  private TableView<Event> eventTable;

  @FXML
  private TableColumn<Event, String> titleColumn;
  @FXML
  private TableColumn<Event, String> locationColumn;
  @FXML
  private TableColumn<Event, String> dateColumn;

  @FXML
  private MenuItem openFile;

  private EventEditor mainApp;

  private EventList events;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    events = new EventList();

    eventTable.setItems(events);
    // Initialize the event table with the two columns.
    titleColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("location"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
    openFile.setOnAction(this::openFile);
  }

  private void openFile(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Choose json...");
    File eventFile = chooser.showOpenDialog(anchorPane.getScene().getWindow());
    if (eventFile != null) {
      try {
        events.load(eventFile.toPath());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param mainApp
   */
  public void setMainApp(EventEditor mainApp) {
    this.mainApp = mainApp;
  }

  private Event createEvent() {
    Event e = new Event();
    e.setTitle("TestTitle");
    e.setLocation("Everywhere");
    e.setDate(new Date(System.currentTimeMillis()));
    e.setAchievements(Collections.emptyList());
    e.setLinks(Collections.emptyList());
    return e;
  }

  static class ValueWrapper extends SimpleStringProperty {
    private final Supplier<String> valueSupplier;

    public static ObservableValue<String> create(Supplier<String> valueSupplier) {
      return new ValueWrapper(valueSupplier);
    }

    private ValueWrapper(Supplier<String> valueSupplier) {
      this.valueSupplier = valueSupplier;
    }

    @Override
    public String get() {
      return valueSupplier.get();
    }
  }
}
