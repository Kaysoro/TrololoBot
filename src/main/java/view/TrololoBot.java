package view;

import controllers.NotificationControl;
import data.Constants;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import util.DiscordClient;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class TrololoBot extends javafx.application.Application {

    private static Stage stage;

    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
            java.lang.reflect.Field charset = java.nio.charset.Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch(Exception e){
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error attempting to force UTF-8 encoding. Default system encoding used for later.");
                alert.showAndWait();
                LoggerFactory.getLogger(TrololoBot.class).error("main", e);
            });
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("frame.fxml"));
        stage = primaryStage;
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(Constants.name + " " + Constants.version);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/trolol.png")));
        NotificationControl.disconnected();
        NotificationControl.updateGuildsNumber();
        NotificationControl.updateEvent(null);

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            if (DiscordClient.DISCORD() != null && DiscordClient.DISCORD().isLoggedIn())
                DiscordClient.DISCORD().logout();
        });
        primaryStage.show();
        ((TreeView<DiscordItem>) primaryStage.getScene().lookup("#tree")).setCellFactory(cellFactory -> new TreeCellImpl());
    }

    public static Stage getStage(){
        return stage;
    }

    private final class TreeCellImpl extends TreeCell<DiscordItem> {
        @Override
        public void updateItem(DiscordItem item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getName());
                setGraphic(item.getNode());
                setContextMenu(item.getMenu());
                setTooltip(item.getToolTip());
            }
            else {
                setText(null);
                setGraphic(null);
                setContextMenu(null);
                setTooltip(null);
            }
        }
    }
}
