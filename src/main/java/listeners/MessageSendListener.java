package listeners;

import data.Channel;
import javafx.scene.Scene;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageSendEvent;

/**
 * Created by kaysoro on 25/09/2018.
 */
public class MessageSendListener extends SceneLinkedListener{

    public MessageSendListener(Scene scene) {
        super(scene);
    }

    @EventSubscriber
    public void onReady(MessageSendEvent event) {
        if (Channel.getChannel() != null && event.getChannel().getLongID() == Channel.getChannel().getLongID()){
            //TODO show message sent
        }
    }
}
