package io.hackergarten.eventeditor;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

/**
 * @author Patrick Reinhart
 */
public class EventEditorController implements Initializable {

  @Override
  public void initialize(URL url, ResourceBundle rb) {
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
