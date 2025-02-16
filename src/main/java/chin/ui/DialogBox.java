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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getChinDialog(String text, Image img, String commandType) {
        System.out.println(text);
        var db = new DialogBox(text, img);
        db.flip();
        String[] parts = text.split(",", 2);
        String startingWord = parts[0];
        db.changeDialogStyle(commandType, startingWord);
        return db;
    }

    private void changeDialogStyle(String commandType, String startingWord) {
        switch (commandType) {
        case "add":
            if (validWords.contains(startingWord)) {
                dialog.getStyleClass().add("add-label");
            } else {
                dialog.getStyleClass().add("error-label");
            }
            break;
        case "mark":
            //folowthrough;
        case "unmark":
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
