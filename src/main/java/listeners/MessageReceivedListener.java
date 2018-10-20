package listeners;

import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import static controllers.ChannelControl.manageMessage;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class MessageReceivedListener {

    @EventSubscriber
    public void onReady(MessageReceivedEvent event) {
        Platform.runLater(() ->  manageMessage(event.getMessage()));
    }
}
