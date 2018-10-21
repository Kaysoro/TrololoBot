package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Message;
import view.TrololoBot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class SendControl  implements Initializable {

    @FXML
    private TextArea myTextArea;

    @FXML
    private void sendText() {
        if (! myTextArea.getText().trim().isEmpty()){
            Message.sendText(ChannelControl.getChannel(), myTextArea.getText());
            myTextArea.setText("");
        }
        else if (myTextArea.getText().matches("\\s+"))
            myTextArea.setText(myTextArea.getText().trim());
    }

    @FXML
    private void shortcutSendText(KeyEvent event) {
        if (! myTextArea.getText().trim().isEmpty() && event.getCode() == KeyCode.ENTER && ! event.isShiftDown()){
            Message.sendText(ChannelControl.getChannel(), myTextArea.getText());
            myTextArea.setText("");
        }
        else if (myTextArea.getText().matches("\\s+") && ! event.isShiftDown())
            myTextArea.setText(myTextArea.getText().trim());
        else if (event.getCode() == KeyCode.ENTER && event.isShiftDown()) {
            myTextArea.setText(myTextArea.getText() + "\n");
            myTextArea.positionCaret(myTextArea.getText().length());
        }
    }

    @FXML
    private void sendEmbed() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/embed.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Send Embed");
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/trolol.png")));
            stage.initOwner(TrololoBot.getStage());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch(Exception e) {
            ExceptionControl.throwException("Send embed - Error", e);
        }
    }

    @FXML
    private void sendImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(TrololoBot.getStage());
        if (file != null && file.exists())
            try {
                Message.sendImage(ChannelControl.getChannel(), ImageIO.read(file), file.getName());
            } catch(IOException e){
                ExceptionControl.throwException("Send image - Error", e);
            }
    }

    @FXML
    private void sendFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(TrololoBot.getStage());
        if (file != null && file.exists())
            try {
                Message.sendFile(ChannelControl.getChannel(), new FileInputStream(file), file.getName());
            } catch(IOException e){
                ExceptionControl.throwException("Send file - Error", e);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}