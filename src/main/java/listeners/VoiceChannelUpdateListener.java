package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelUpdateEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class VoiceChannelUpdateListener {

    @EventSubscriber
    public void onReady(VoiceChannelUpdateEvent event) {
        DiscordRegistry.get(IVoiceChannel.class, event.getNewVoiceChannel().getLongID()).checkIntegrity();
        Platform.runLater(() -> NotificationControl.updateEvent("Voice channel updated: "
                + event.getGuild().getName() + " > " + event.getNewVoiceChannel().getName()));

    }
}
