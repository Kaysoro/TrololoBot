package controllers;

import data.Constants;
import data.DiscordSceneConstants;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import listeners.ReadyListener;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.Image;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;
import util.DiscordClient;
import view.TrololoBot;
import view.tree.DiscordItem;

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
                        TreeView<DiscordItem> tree = (TreeView<DiscordItem>) TrololoBot.getStage().getScene().lookup("#tree");
                        tree.setRoot(null);
                        DiscordClient.connectToDiscord(token);
                        DiscordClient.DISCORD().getDispatcher().registerListener(new ReadyListener(
                                tree, usernameMenuItem, avatarMenuItem, dispatcherMenuItem));
                    } catch (DiscordException e){
                        ExceptionControl.throwException("Discord login - Error", e);
                        NotificationControl.disconnected();
                        NotificationControl.updateGuildsNumber();
                        NotificationControl.updateEvent(null);
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
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Change username");
                                alert.setHeaderText(null);
                                alert.setContentText("Username change succeeded. It will take some time to see the effects.");
                                alert.showAndWait();
                            });
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
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Change dispatcher");
        dialog.setHeaderText(null);

        ComboBox<StatusType> statusTypeComboBox = new ComboBox<>();
        statusTypeComboBox.getItems().addAll(StatusType.values());
        statusTypeComboBox.setValue(DiscordClient.DISCORD().getOurUser().getPresence().getStatus());
        ComboBox<ActivityType> activityTypeComboBox = new ComboBox<>();
        activityTypeComboBox.getItems().addAll(ActivityType.values());
        activityTypeComboBox.setValue(DiscordClient.DISCORD().getOurUser().getPresence().getActivity().orElse(null));
        TextField text = new TextField(DiscordClient.DISCORD().getOurUser().getPresence().getText().orElse(null));
        GridPane grid = new GridPane();
        grid.add(new Label("Status: "), 1, 1);
        grid.add(statusTypeComboBox, 2, 1);
        grid.add(new Label("Activity: "), 1, 2);
        grid.add(activityTypeComboBox, 2, 2);
        grid.add(new Label("Text: "), 1, 3);
        grid.add(text, 2, 3);
        grid.setPadding(new Insets(5, 5, 5, 5));
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ButtonType button = dialog.showAndWait().orElse(ButtonType.CANCEL);
        if (button.equals(ButtonType.OK))
            RequestBuffer.request(() -> {
                try {
                    DiscordClient.DISCORD().changePresence(statusTypeComboBox.getValue(), activityTypeComboBox.getValue(), text.getText());
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Change Dispatcher");
                        alert.setHeaderText(null);
                        alert.setContentText("Dispatcher change succeeded. It will take some time to see the effects.");
                        alert.showAndWait();
                    });
                } catch (RateLimitException e) {
                    ExceptionControl.throwException("Change Dispatcher - Error", e);
                    throw e;
                } catch (DiscordException e) {
                    ExceptionControl.throwException("Change Dispatcher - Error", e);
                }
            });
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