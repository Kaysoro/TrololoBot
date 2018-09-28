package listeners;

import data.Channel;
import javafx.scene.Scene;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class MessageReceivedListener extends SceneLinkedListener{

    public MessageReceivedListener(Scene scene) {
        super(scene);
    }

    @EventSubscriber
    public void onReady(MessageReceivedEvent event) {
        if (Channel.getChannel() != null && event.getChannel().getLongID() == Channel.getChannel().getLongID()){
            // TODO show text
        }
        else {
            // TODO manage notifs
        }
    }
}
