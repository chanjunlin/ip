package chin.ui;

import java.io.IOException;
import java.util.Objects;

import chin.main.ChinChin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Test
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/avatar.png")));
    private Image chinChinImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chill.png")));

    private ChinChin chinChin = new ChinChin("data/ChinChinTaskList.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(417);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChin(chinChin);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
