package controllers;

import data.DiscordSceneConstants;
import javafx.scene.control.Label;
import util.DiscordClient;
import view.TrololoBot;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class NotificationControl {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.FRANCE)
                    .withZone(ZoneId.systemDefault());

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

    public static void updateGuildsNumber(){
        Label stats = ((Label) TrololoBot.getStage().getScene().lookup("#stats"));
        if (DiscordClient.DISCORD() != null && DiscordClient.DISCORD().isLoggedIn() && DiscordClient.DISCORD().isReady())
            stats.setText(DiscordClient.DISCORD().getGuilds().size() + " guild(s) with "
                    + GuildControl.getCountVulnerableGuilds() + " vulnerable");
        else
            stats.setText("No further information");
    }

    public synchronized static void updateEvent(String text){
        Label stats = ((Label) TrololoBot.getStage().getScene().lookup("#events"));
        if (DiscordClient.DISCORD() != null && DiscordClient.DISCORD().isLoggedIn() && DiscordClient.DISCORD().isReady())
            stats.setText("[" + formatter.format(Instant.now()) + "] " + text);
        else
            stats.setText("No further information");
    }
}