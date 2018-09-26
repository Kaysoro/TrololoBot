package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class MenuControl implements Initializable {

    private final static Logger LOG = LoggerFactory.getLogger(MenuControl.class);

    @FXML
    private void changeUsername(){
        LOG.info("change username");
        // TODO
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
    private void getInvite()
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}