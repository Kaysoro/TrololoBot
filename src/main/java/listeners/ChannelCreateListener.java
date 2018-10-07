package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCreateEvent;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelCreateListener {

    @EventSubscriber
    public void onReady(ChannelCreateEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Channel created: "
                + event.getGuild().getName() + " > " + event.getChannel().getName()));
    }
}
