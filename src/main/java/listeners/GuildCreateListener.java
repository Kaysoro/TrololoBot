package listeners;

import controllers.GuildControl;
import controllers.NotificationControl;
import javafx.application.Platform;
import javafx.scene.control.TreeView;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import view.TrololoBot;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildCreateListener {

    @EventSubscriber
    public void onReady(GuildCreateEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Guild added: " + event.getGuild().getName());
            ((TreeView<DiscordItem>) TrololoBot.getStage().getScene().lookup("#tree"))
                .getRoot().getChildren().add(GuildControl.createGuild(event.getGuild()));
        });
    }
}
