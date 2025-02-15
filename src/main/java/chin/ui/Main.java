package chin.ui;

import java.io.IOException;

import chin.main.ChinChin;
import chin.util.ChinChinException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Test
 */
public class Main extends Application {

    private final ChinChin chinChin;

    public Main() throws ChinChinException {
        this.chinChin = new ChinChin("data/ChinChinTaskList.txt");
    }

    @Override
    public void start(Stage stage) {
        try {
            loadMainWindow(stage);
        } catch (IOException e) {
            showErrorDialog("Failed to load the main window: " + e.getMessage());
        }
    }

    private void loadMainWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setChin(this.chinChin);
        stage.show();
    }

    private void showErrorDialog(String message) {
        javafx.scene.control.Alert alertMessage =
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alertMessage.setTitle("Error");
        alertMessage.setHeaderText(null);
        alertMessage.setContentText(message);

        alertMessage.showAndWait();
    }
}
