package listeners;

import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageSendEvent;

import static controllers.ChannelControl.manageMessage;

/**
 * Created by kaysoro on 25/09/2018.
 */
public class MessageSendListener {

    @EventSubscriber
    public void onReady(MessageSendEvent event) {
        Platform.runLater(() -> manageMessage(event.getMessage()));
    }
}
