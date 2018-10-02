package controllers;

import data.DiscordSceneConstants;
import javafx.scene.control.Label;
import util.DiscordClient;
import view.TrololoBot;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class NotificationControl {

    public static void connecting(){
        Label status = ((Label) TrololoBot.getStage().getScene().lookup("#status"));
        status.setText("Connecting...");
        status.setGraphic(DiscordSceneConstants.connectingIcon);
    }

    public static void connected(){
        Label status = ((Label) TrololoBot.getStage().getScene().lookup("#status"));
        status.setText("Connected");
        status.setGraphic(DiscordSceneConstants.connectedIcon);
    }

    public static void disconnected(){
        Label status = ((Label) TrololoBot.getStage().getScene().lookup("#status"));
        status.setText("Disconnected");
        status.setGraphic(DiscordSceneConstants.disconnectedIcon);
    }

    public static void updateGuildsNumber(int guildsNumber, int vulnerableGuilds){
        Label stats = ((Label) TrololoBot.getStage().getScene().lookup("#stats"));
        if (DiscordClient.DISCORD() != null && DiscordClient.DISCORD().isLoggedIn() && DiscordClient.DISCORD().isReady())
            stats.setText(guildsNumber + " guild(s) with " + vulnerableGuilds + " vulnerable");
        else
            stats.setText("No further information");
    }
}