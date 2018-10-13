package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCategoryUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelUpdateEvent;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class VoiceChannelUpdateListener {

    @EventSubscriber
    public void onReady(ChannelUpdateEvent event) {
        if (!(event instanceof ChannelCategoryUpdateEvent)) {
            DiscordRegistry.get(IChannel.class, event.getNewChannel().getLongID()).checkIntegrity();
            Platform.runLater(() -> NotificationControl.updateEvent("Voice channel updated: "
                    + event.getGuild().getName() + " > " + event.getNewChannel().getName()));
        }
    }
}
