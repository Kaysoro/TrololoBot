package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelDeleteEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelDeleteListener {

    @EventSubscriber
    public void onReady(ChannelDeleteEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Channel deleted: "
                + event.getGuild().getName() + " > " + event.getChannel().getName());

            if (event.getChannel().getCategory() != null)
                DiscordRegistry.get(ICategory.class, event.getChannel().getCategory().getLongID())
                        .getTreeItem().getChildren().remove(
                                DiscordRegistry.get(IChannel.class, event.getChannel().getLongID()).getTreeItem());
            else
                DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).getTreeItem().getChildren()
                        .remove(DiscordRegistry.get(IChannel.class, event.getChannel().getLongID()).getTreeItem());
        });
        DiscordRegistry.remove(IChannel.class, event.getChannel().getLongID());
    }
}
