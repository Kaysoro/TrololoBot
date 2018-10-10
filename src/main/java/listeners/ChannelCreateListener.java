package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import javafx.scene.control.TreeItem;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCreateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IGuild;
import view.tree.ChannelItem;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class ChannelCreateListener {

    @EventSubscriber
    public void onReady(ChannelCreateEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Channel created: "
                    + event.getGuild().getName() + " > " + event.getChannel().getName());
            TreeItem<DiscordItem> chanItem = new TreeItem<>();
            chanItem.setValue(ChannelItem.of(event.getChannel(), chanItem));

            if (event.getChannel().getCategory() != null)
                DiscordRegistry.get(ICategory.class, event.getChannel().getCategory().getLongID())
                        .getTreeItem().getChildren().add(chanItem);
            else
                DiscordRegistry.get(IGuild.class, event.getGuild().getLongID())
                        .getTreeItem().getChildren().add(chanItem);
        });
    }
}
