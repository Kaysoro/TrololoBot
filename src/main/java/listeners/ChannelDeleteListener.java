package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelDeleteEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class ChannelDeleteListener {

    private final static Logger LOG = LoggerFactory.getLogger(ChannelDeleteListener.class);

    public ChannelDeleteListener(){
        super();
    }

    @EventSubscriber
    public void onReady(ChannelDeleteEvent event) {
        //TODO
    }
}
