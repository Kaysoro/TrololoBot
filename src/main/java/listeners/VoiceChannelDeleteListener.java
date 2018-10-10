package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelCreateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class VoiceChannelDeleteListener {

    @EventSubscriber
    public void onReady(VoiceChannelCreateEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Voice Channel deleted: "
                + event.getGuild().getName() + " > " + event.getVoiceChannel().getName());
            if (event.getVoiceChannel().getCategory() != null)
                DiscordRegistry.get(ICategory.class, event.getVoiceChannel().getCategory().getLongID())
                        .getTreeItem().getChildren().remove(
                        DiscordRegistry.get(IVoiceChannel.class, event.getVoiceChannel().getLongID()).getTreeItem());
            else
                DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).getTreeItem().getChildren()
                        .remove(DiscordRegistry.get(IVoiceChannel.class, event.getVoiceChannel().getLongID()).getTreeItem());
        });
        DiscordRegistry.remove(IVoiceChannel.class, event.getVoiceChannel().getLongID());
    }
}
