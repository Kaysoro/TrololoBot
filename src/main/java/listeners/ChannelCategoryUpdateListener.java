package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import javafx.scene.control.TreeItem;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCategoryUpdateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelCategoryUpdateListener {

    @EventSubscriber
    public void onReady(ChannelCategoryUpdateEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Channel Category updated: "
                    + event.getGuild().getName() + " > " + event.getNewChannel().getName());
            DiscordItem channel = DiscordRegistry.get(event.getNewChannel() instanceof IVoiceChannel ?
                    IVoiceChannel.class : IChannel.class, event.getNewChannel().getLongID());
            TreeItem<DiscordItem> oldParent = DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).getTreeItem();
            TreeItem<DiscordItem> newParent = DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).getTreeItem();
            if (event.getOldCategory() != null)
                oldParent = DiscordRegistry.get(ICategory.class, event.getOldCategory().getLongID()).getTreeItem();
            if (event.getNewCategory() != null)
                newParent = DiscordRegistry.get(ICategory.class, event.getNewCategory().getLongID()).getTreeItem();

            oldParent.getChildren().remove(channel.getTreeItem());
            newParent.getChildren().add(channel.getTreeItem());
            newParent.getValue().checkIntegrity();
            channel.checkIntegrity();
        });
    }
}
