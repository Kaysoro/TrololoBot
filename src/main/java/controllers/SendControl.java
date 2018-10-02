package controllers;

import data.Channel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
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
            Message.sendText(Channel.getChannel(), myTextArea.getText());
            myTextArea.setText("");
        }
        else if (myTextArea.getText().matches("\\s+"))
            myTextArea.setText(myTextArea.getText().trim());
    }

    @FXML
    private void shortcutSendText(KeyEvent event) {
        if (! myTextArea.getText().trim().isEmpty() && event.getCode() == KeyCode.ENTER && ! event.isShiftDown()){
            Message.sendText(Channel.getChannel(), myTextArea.getText());
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
        // TODO
    }

    @FXML
    private void sendImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(TrololoBot.getStage());
        if (file != null && file.exists())
            try {
                Message.sendImage(Channel.getChannel(), ImageIO.read(file), file.getName());
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
                Message.sendFile(Channel.getChannel(), new FileInputStream(file), file.getName());
            } catch(IOException e){
                ExceptionControl.throwException("Send file - Error", e);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}