package io.hackergarten.eventeditor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import io.hackergarten.eventeditor.model.Event;
import io.hackergarten.eventeditor.model.EventList;
import io.hackergarten.eventeditor.model.Link;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Patrick Reinhart
 */
public class EventEditorController implements Initializable {
  private final EventList events;

  private EventEditor mainApp;

  @FXML
  private AnchorPane anchorPane;

  // event table content
  @FXML
  private TableView<Event> eventTable;
  @FXML
  private TableColumn<Event, String> eventTableTitleColumn;
  @FXML
  private TableColumn<Event, String> eventTableLocationColumn;
  @FXML
  private TableColumn<Event, String> eventTableVenueColumn;
  @FXML
  private TableColumn<Event, String> eventTableDateColumn;


  // details of a single event
  @FXML
  private TextField eventTitle;
  @FXML
  private TextField eventVenue;
  @FXML
  private TextField eventLocation;
  @FXML
  private DatePicker eventDate;
  @FXML
  private TextArea eventDetails;

  @FXML
  private TableView<Link> linksTable;
  @FXML
  private TableColumn<Link, String> linksTableTitleColumn;
  @FXML
  private TableColumn<Link, String> linksTableUrlColumn;

  @FXML
  private TableView<Link> achievementsTable;
  @FXML
  private TableColumn<Link, String> achievementsTableTitleColumn;
  @FXML
  private TableColumn<Link, String> achievementsTableUrlColumn;

  // menu entries
  @FXML
  private MenuItem openFile;


  public EventEditorController() {
    events = new EventList();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // initialize events table
    eventTable.setItems(events);
    TableViewSelectionModel<Event> selectionModel = eventTable.getSelectionModel();
    selectionModel.setSelectionMode(SelectionMode.SINGLE);
    selectionModel.selectedItemProperty().addListener(this::updateBindings);
    // Initialize the event table with the two columns.
    eventTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    eventTableVenueColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
    eventTableLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    eventTableDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    // links
    linksTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    linksTableUrlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
    // achievements
    achievementsTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    achievementsTableUrlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
    // other stuff
    openFile.setOnAction(this::openFile);
  }

  private void updateBindings(ObservableValue<? extends Event> observable, Event oldValue,
      Event newValue) {
    if (oldValue != null) {
      eventDate.valueProperty().unbindBidirectional(oldValue.dateProperty());
      eventDetails.textProperty().unbindBidirectional(oldValue.detailsProperty());
      eventLocation.textProperty().unbindBidirectional(oldValue.locationProperty());
      eventTitle.textProperty().unbindBidirectional(oldValue.titleProperty());
      eventVenue.textProperty().unbindBidirectional(oldValue.venueProperty());
      linksTable.itemsProperty().unbindBidirectional(oldValue.linksProperty());
      achievementsTable.itemsProperty().unbindBidirectional(oldValue.achievementsProperty());
    }
    if (newValue != null) {
      eventDate.valueProperty().bindBidirectional(newValue.dateProperty());
      eventDetails.textProperty().bindBidirectional(newValue.detailsProperty());
      eventLocation.textProperty().bindBidirectional(newValue.locationProperty());
      eventTitle.textProperty().bindBidirectional(newValue.titleProperty());
      eventVenue.textProperty().bindBidirectional(newValue.venueProperty());
      linksTable.itemsProperty().bindBidirectional(newValue.linksProperty());
      achievementsTable.itemsProperty().bindBidirectional(newValue.achievementsProperty());
    }
  }

  private void openFile(ActionEvent event) {
    Preferences prefs = mainApp.getPreferences();
    ExtensionFilter filter = new ExtensionFilter("Hackergarten events", "events.json");
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Choose json...");
    chooser.getExtensionFilters().add(filter);
    chooser.setSelectedExtensionFilter(filter);
    chooser.setInitialDirectory(
        Paths.get(prefs.get("lastOpenFileDirectory", System.getProperty("user.dir"))).toFile());
    File eventFile = chooser.showOpenDialog(anchorPane.getScene().getWindow());
    if (eventFile != null) {
      try {
        Path eventFilePath = eventFile.toPath();
        events.load(eventFilePath);
        prefs.put("lastOpenFileDirectory", eventFilePath.getParent().toString());
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
}
