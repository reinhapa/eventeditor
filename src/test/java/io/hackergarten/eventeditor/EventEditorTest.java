package io.hackergarten.eventeditor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoSettings;

@MockitoSettings
class EventEditorTest {
    private EventEditor editor;

    @BeforeEach
    void setUp() {
        editor = new EventEditor();
    }

    @Test
    void testGetPreferences() {
        Preferences prefs = editor.getPreferences();
        assertThat(prefs.absolutePath()).isEqualTo("/io/hackergarten/eventeditor");
    }

    @Test
    void testStart(@Mock Stage stage, @Mock Parent root,
                          @Mock EventEditorController controller, @Mock Scene scene)
            throws Exception {
        final ObservableList<String> stylesheets = FXCollections.observableArrayList();
        try (MockedConstruction<FXMLLoader> fxmlLoader = mockConstruction(FXMLLoader.class, (loader, context) -> {
            when(loader.load()).thenReturn(root);
            when(loader.getController()).thenReturn(controller);
        })) {
            when(scene.getStylesheets()).thenReturn(stylesheets);

            editor.start(stage);

            assertThat(stylesheets).hasSize(1);
        }
    }

    @Test
    void testMain() {
        String[] args = {"a", "b", "c"};
        try (MockedStatic<Application> application = mockStatic(Application.class)) {
            EventEditor.main(args);

            application.verify(() -> Application.launch(args));
        }
    }
}
