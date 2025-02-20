package chin.ui;

import java.io.IOException;

import chin.main.ChinChin;
import chin.util.ChinChinException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The main entry point for the ChinChin application.
 */
public class Main extends Application {

    private final ChinChin chinChin;

    /**
     * Constructs a Main instance and initialises the ChinChin task manager.
     *
     * @throws ChinChinException If there is an error initializing the task manager with the specified data file.
     */
    public Main() throws ChinChinException {
        this.chinChin = new ChinChin("data/ChinChinTaskList.txt");
    }

    /**
     * Starts the JavaFX application by loading the main window.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            loadMainWindow(stage);
        } catch (IOException e) {
            showErrorDialog("Failed to load the main window: " + e.getMessage());
        }
    }

    /**
     * Loads and displays the main application window.
     *
     * @param stage The primary stage on which to display the loaded scene.
     *
     * @throws IOException If there is an error loading the FXML file for
     *                     the main window layout.
     */
    private void loadMainWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setChin(this.chinChin);
        stage.show();
    }

    /**
     * Displays an error dialog with a specified message.
     *
     * @param message The message to be displayed in the alert dialog box.
     */
    private void showErrorDialog(String message) {
        javafx.scene.control.Alert alertMessage =
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alertMessage.setTitle("Error");
        alertMessage.setHeaderText(null);
        alertMessage.setContentText(message);

        alertMessage.showAndWait();
    }
}
