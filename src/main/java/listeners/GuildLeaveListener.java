package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildLeaveEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildLeaveListener {

    private final static Logger LOG = LoggerFactory.getLogger(GuildLeaveListener.class);

    @EventSubscriber
    public void onReady(GuildLeaveEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Guild losed: " + event.getGuild().getName()));
        // TODO remove guild
    }
}
