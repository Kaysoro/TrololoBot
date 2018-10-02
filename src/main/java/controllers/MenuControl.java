package controllers;

import data.Constants;
import data.DiscordSceneConstants;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import listeners.ReadyListener;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.Image;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;
import util.DiscordClient;
import view.TrololoBot;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class MenuControl implements Initializable {

    @FXML
    private MenuItem usernameMenuItem;

    @FXML
    private MenuItem avatarMenuItem;

    @FXML
    private MenuItem dispatcherMenuItem;

    @FXML
    private void loginDiscord(){
        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Discord login");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter the token:");
            dialog.showAndWait().ifPresent(token -> {
                if (! token.isEmpty())
                    try {
                        usernameMenuItem.setDisable(true);
                        avatarMenuItem.setDisable(true);
                        dispatcherMenuItem.setDisable(true);
                        NotificationControl.connecting();
                        TreeView<String> tree = (TreeView<String>) TrololoBot.getStage().getScene().lookup("#tree");
                        tree.setRoot(null);
                        DiscordClient.connectToDiscord(token);
                        DiscordClient.DISCORD().getDispatcher().registerListener(new ReadyListener(
                                tree, usernameMenuItem, avatarMenuItem, dispatcherMenuItem));
                    } catch (DiscordException e){
                        ExceptionControl.throwException("Discord login - Error", e);
                        NotificationControl.disconnected();
                    }
            });
        });
    }

    @FXML
    private void changeUsername(){
        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog(DiscordClient.DISCORD().getOurUser().getName());
            dialog.setTitle("Change username");
            dialog.setHeaderText("Take care about the rate limit : username cannot be changed very often.");
            dialog.setContentText("Please enter the new username:");
            dialog.showAndWait().ifPresent(name -> {
                if (!DiscordClient.DISCORD().getOurUser().getName().equals(name) && ! name.isEmpty())
                    RequestBuffer.request(() -> {
                        try {
                            DiscordClient.DISCORD().changeUsername(name);
                        } catch (RateLimitException e){
                            ExceptionControl.throwException("Change username - Error", e);
                            throw e;
                        } catch (DiscordException e){
                            ExceptionControl.throwException("Change username - Error", e);
                        }
                    });
                });
        });
    }

    @FXML
    private void changeAvatar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(TrololoBot.getStage());
        if (file != null && file.exists()) {
            RequestBuffer.request(() -> {
                try {
                    DiscordClient.DISCORD().changeAvatar(Image.forFile(file));
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Change Avatar");
                        alert.setHeaderText(null);
                        alert.setContentText("Avatar change succeeded. It will take some time to see the effects.");
                        alert.showAndWait();
                    });
                } catch (RateLimitException e) {
                    ExceptionControl.throwException("Change avatar - Error", e);
                    throw e;
                } catch (DiscordException e) {
                    ExceptionControl.throwException("Change avatar - Error", e);
                }
            });
        }
    }

    @FXML
    private void changeDispatcher() {
        // TODO
    }

    @FXML
    private void about(){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setGraphic(DiscordSceneConstants.aboutIcon);
            alert.setContentText(Constants.name + " - version " + Constants.version + "\n"
            + "A Github hosted project by KΔYϟ0R0\n" + Constants.git);
            alert.showAndWait();
        });
    }

    @FXML
    private void quit(){
        Platform.exit();
        if (DiscordClient.DISCORD() != null && DiscordClient.DISCORD().isLoggedIn())
            DiscordClient.DISCORD().logout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}