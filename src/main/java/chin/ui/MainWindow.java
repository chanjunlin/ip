package chin.ui;

import java.util.Objects;

import chin.main.ChinChin;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChinChin chinChin;

    private final Image userImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/avatar.png")));
    private final Image chinChinImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chill.png")));

    /**
     * Initialise the program
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayGreeting();
    }

    /**
     * Injects the Duke instance
     */
    public void setChin(ChinChin c) {
        this.chinChin = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String chinText = chinChin.processUserInput(userInput.getText());
        String commandType = chinChin.getCommandType();
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getChinDialog(chinText, chinChinImage, commandType)
        );
        userInput.clear();


        if (commandType.equals("exit")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                System.exit(0);
            });
            pause.play();
        }
    }

    /**
     * Display the greeting message to the user when the application first starts.
     */
    private void displayGreeting() {
        String greetingMessage = "Nihao, I'm ChinChin\nWhat you want?";
        DialogBox greetingDialog = DialogBox.getChinDialog(greetingMessage, chinChinImage, "greetings");
        dialogContainer.getChildren().add(greetingDialog);
    }
}
