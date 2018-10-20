package listeners;

import controllers.ChannelControl;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class MessageReceivedListener {


    @EventSubscriber
    public void onReady(MessageReceivedEvent event) {
        if (ChannelControl.getChannel() != null && event.getChannel().getLongID() == ChannelControl.getChannel().getLongID()){
            // TODO show text
        }
    }
}
