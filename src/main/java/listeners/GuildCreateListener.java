package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildCreateListener {

    @EventSubscriber
    public void onReady(GuildCreateEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Guild added: " + event.getGuild().getName()));
        // TODO add to tree
    }
}
