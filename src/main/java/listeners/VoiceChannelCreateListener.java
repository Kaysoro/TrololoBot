package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCreateEvent;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelCreateEvent;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class VoiceChannelCreateListener {

    @EventSubscriber
    public void onReady(VoiceChannelCreateEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Voice Channel created: "
                + event.getGuild().getName() + " > " + event.getVoiceChannel().getName()));
    }
}
