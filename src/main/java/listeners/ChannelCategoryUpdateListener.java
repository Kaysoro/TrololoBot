package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCategoryUpdateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelCategoryUpdateListener {

    @EventSubscriber
    public void onReady(ChannelCategoryUpdateEvent event) {
        // TODO manage all cases
        DiscordRegistry.get(ICategory.class, event.getNewCategory().getLongID()).checkIntegrity();
        DiscordRegistry.get(IChannel.class, event.getNewChannel().getLongID()).checkIntegrity();
        Platform.runLater(() -> NotificationControl.updateEvent("Channel Category updated: "
                + event.getGuild().getName() + " > " + event.getNewChannel().getName()));
    }
}
