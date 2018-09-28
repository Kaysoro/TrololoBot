import controllers.NotificationControl;
import data.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import listeners.ReadyListener;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import util.ClientConfig;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class Main extends Application {

    public static void main(String[] args) {
        LoggerFactory.getLogger(Main.class).info("================================================\n" +
                "                                ___  __   __        __        __   __   __  ___ \n" +
                "                                 |  |__) /  \\ |    /  \\ |    /  \\ |__) /  \\  |\n" +
                "                                 |  |  \\ \\__/ |___ \\__/ |___ \\__/ |__) \\__/  |     Version " + Constants.version + "\n" +
                "                                ================================================");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/frame.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(Constants.name + " " + Constants.version);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/trolol.png")));
        NotificationControl.disconnected(primaryStage.getScene());
        NotificationControl.updateGuildsNumber(primaryStage.getScene());

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            ClientConfig.DISCORD().logout();
        });
        primaryStage.show();

        Platform.runLater(() -> {
            NotificationControl.connecting(primaryStage.getScene());
            IDiscordClient client = ClientConfig.DISCORD();
            client.getDispatcher().registerListener(new ReadyListener(primaryStage.getScene()));
        });
    }
}
