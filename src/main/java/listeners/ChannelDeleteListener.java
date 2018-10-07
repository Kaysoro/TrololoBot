package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelDeleteEvent;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelDeleteListener {

    @EventSubscriber
    public void onReady(ChannelDeleteEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Channel deleted: "
                + event.getGuild().getName() + " > " + event.getChannel().getName()));
    }
}
