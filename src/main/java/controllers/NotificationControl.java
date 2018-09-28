package controllers;

import data.DiscordSceneConstants;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ClientConfig;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class NotificationControl {

    private final static Logger LOG = LoggerFactory.getLogger(NotificationControl.class);

    public static void connecting(Scene scene){
        Label status = ((Label) scene.lookup("#status"));
        status.setText("Connecting...");
        status.setGraphic(DiscordSceneConstants.connectingIcon);
    }

    public static void connected(Scene scene){
        Label status = ((Label) scene.lookup("#status"));
        status.setText("Connected");
        status.setGraphic(DiscordSceneConstants.connectedIcon);
    }

    public static void disconnected(Scene scene){
        Label status = ((Label) scene.lookup("#status"));
        status.setText("Disconnected");
        status.setGraphic(DiscordSceneConstants.disconnectedIcon);
    }

    public static void updateGuildsNumber(Scene scene){
        Label stats = ((Label) scene.lookup("#stats"));
        if (ClientConfig.DISCORD().isLoggedIn() && ClientConfig.DISCORD().isReady()) {
            stats.setText("Number of Guild(s): " + ClientConfig.DISCORD().getGuilds().size());
            stats.setGraphic(new ImageView(DiscordSceneConstants.guildIcon));
        }
        else {
            stats.setText("No further information");
        }
    }
}