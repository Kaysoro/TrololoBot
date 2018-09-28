package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;
import util.ClientConfig;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class MenuControl implements Initializable {

    private final static Logger LOG = LoggerFactory.getLogger(MenuControl.class);

    @FXML
    private void changeUsername(){
        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog(ClientConfig.DISCORD().getOurUser().getName());
            dialog.setTitle("Change username");
            dialog.setHeaderText("Take care about the rate limit : username cannot be changed very often.");
            dialog.setContentText("Please enter the new username:");
            dialog.showAndWait().ifPresent(name -> {
                if (!ClientConfig.DISCORD().getOurUser().getName().equals(name))
                    RequestBuffer.request(() -> {
                        try {
                            ClientConfig.DISCORD().changeUsername(name);
                        } catch (DiscordException e){
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Change username - Error");
                                alert.setHeaderText("Error found : please read the reason below");
                                alert.setContentText(e.getMessage());
                                alert.showAndWait();
                            });
                        }
                    });
                });
        });
    }

    @FXML
    private void changeAvatar()
    {
        LOG.info("change avatar");
        // TODO

    }

    @FXML
    private void changeDispatcher()
    {
        LOG.info("change dispatcher");
        // TODO
    }

    @FXML
    private void about(){
        LOG.info("about");
        // TODO
    }

    @FXML
    private void quit(){
        Platform.exit();
        ClientConfig.DISCORD().logout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}