package listeners;

import controllers.ChannelControl;
import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelUpdateEvent;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelUpdateListener {

    @EventSubscriber
    public void onReady(ChannelUpdateEvent event) {
        DiscordRegistry.get(IChannel.class, event.getNewChannel().getLongID()).checkIntegrity();
        Platform.runLater(() -> NotificationControl.updateEvent("Channel updated: "
                + event.getGuild().getName() + " > " + event.getNewChannel().getName()));
        if (ChannelControl.getChannel() != null && ChannelControl.getChannel().getLongID() == event.getNewChannel().getLongID())
            ChannelControl.checkActionChannel(event.getNewChannel());
    }
}
