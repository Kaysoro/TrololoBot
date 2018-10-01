package view;

import controllers.NotificationControl;
import data.Constants;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.DiscordClient;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class TrololoBot extends javafx.application.Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("frame.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(Constants.name + " " + Constants.version);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/trolol.png")));
        NotificationControl.disconnected();
        NotificationControl.updateGuildsNumber();

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            if (DiscordClient.DISCORD() != null && DiscordClient.DISCORD().isLoggedIn())
                DiscordClient.DISCORD().logout();
        });
        primaryStage.show();
    }

    public static Stage getStage(){
        return stage;
    }
}
