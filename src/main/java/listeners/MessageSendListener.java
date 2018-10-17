package listeners;

import controllers.ChannelControl;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageSendEvent;

/**
 * Created by kaysoro on 25/09/2018.
 */
public class MessageSendListener {

    @EventSubscriber
    public void onReady(MessageSendEvent event) {
        if (ChannelControl.getChannel() != null && event.getChannel().getLongID() == ChannelControl.getChannel().getLongID()){
            //TODO show message sent
        }
    }
}
