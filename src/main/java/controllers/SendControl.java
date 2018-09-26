package controllers;

import data.Channel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Message;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class SendControl  implements Initializable {

    private final static Logger LOG = LoggerFactory.getLogger(SendControl.class);

    @FXML
    private TextArea myTextArea;

    @FXML
    private void sendText()
    {
        if (Channel.getChannel() != null && ! myTextArea.getText().isEmpty()){
            Message.sendText(Channel.getChannel(), myTextArea.getText());
            myTextArea.setText("");
        }
    }

    @FXML
    private void shortcutSendText(KeyEvent event)
    {
        if (Channel.getChannel() != null && ! myTextArea.getText().isEmpty() && event.getCode() == KeyCode.ENTER){
            Message.sendText(Channel.getChannel(), myTextArea.getText());
            myTextArea.setText("");
        }
    }

    @FXML
    private void sendEmbed()
    {
        if (Channel.getChannel() != null){
            LOG.info("send embed action");
            // TODO
        }
    }

    @FXML
    private void sendImage(){
        LOG.info("send image action");
        // TODO
    }

    @FXML
    private void sendFile(){
        LOG.info("send file action");
        // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}