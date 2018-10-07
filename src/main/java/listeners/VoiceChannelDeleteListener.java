package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelCreateEvent;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class VoiceChannelDeleteListener {

    @EventSubscriber
    public void onReady(VoiceChannelCreateEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Voice Channel deleted: "
                + event.getGuild().getName() + " > " + event.getVoiceChannel().getName()));
    }
}
