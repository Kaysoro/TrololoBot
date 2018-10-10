package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import javafx.scene.control.TreeItem;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelCreateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IGuild;
import view.tree.DiscordItem;
import view.tree.VoiceItem;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class VoiceChannelCreateListener {

    @EventSubscriber
    public void onReady(VoiceChannelCreateEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Voice Channel created: "
                + event.getGuild().getName() + " > " + event.getVoiceChannel().getName());
            TreeItem<DiscordItem> chanItem = new TreeItem<>();
            chanItem.setValue(VoiceItem.of(event.getVoiceChannel(), chanItem));

            if (event.getVoiceChannel().getCategory() != null)
                DiscordRegistry.get(ICategory.class, event.getVoiceChannel().getCategory().getLongID())
                        .getTreeItem().getChildren().add(chanItem);
            else
                DiscordRegistry.get(IGuild.class, event.getGuild().getLongID())
                        .getTreeItem().getChildren().add(chanItem);
        });
    }
}
