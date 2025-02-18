package chin.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final List<String> validWords = Arrays.asList("Oki", "your", "why", "you", "put", "bro", "if", "no");

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in this dialog box.
     * @param img  The image to be displayed as the speaker's profile picture.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setFitHeight(50);
        displayPicture.setFitWidth(50);
        displayPicture.setPreserveRatio(true);
        Circle clip = new Circle(25);
        clip.setCenterX(25);
        clip.setCenterY(25);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a DialogBox representing the user's dialog.
     *
     * @param text The message to be displayed in the dialog box.
     * @param img  The image representing the user's profile picture.
     *
     * @return A DialogBox containing the provided text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox representing Chin's response.
     *
     * @param text        The message to be displayed in Chin's dialog box.
     * @param img         The image representing Chin's avatar/profile picture.
     * @param commandType The type of command that determines how Chin's dialog should be styled
     *
     * @return A styled DialogBox containing Chin's response along with customized visuals.
     */
    public static DialogBox getChinDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        String[] parts = text.split(",", 2);
        String startingWord = parts[0];
        db.changeDialogStyle(commandType, startingWord);
        return db;
    }

    /**
     * Updates the styling of this dialog box based on its content and command type.
     *
     * @param commandType  The type of command being processed
     * @param startingWord The first word in the text input, used to determine if special styling
     *                     applies.
     */
    private void changeDialogStyle(String commandType, String startingWord) {
        switch (commandType) {
        case "add":
            if (validWords.contains(startingWord)) {
                dialog.getStyleClass().add("add-label");
            } else {
                dialog.getStyleClass().add("error-label");
            }
            break;
        case "mark", "unmarked":
            dialog.getStyleClass().add("marked-label");
            break;
        case "delete":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }
}
