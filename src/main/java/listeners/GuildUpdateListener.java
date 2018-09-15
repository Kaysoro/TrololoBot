package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildUpdateEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildUpdateListener {
    private final static Logger LOG = LoggerFactory.getLogger(GuildUpdateListener.class);

    @EventSubscriber
    public void onReady(GuildUpdateEvent event) {
        if (! event.getOldGuild().getName().equals(event.getNewGuild().getName()))
            LOG.info("'" + event.getOldGuild().getName() + "' -> '" + event.getNewGuild().getName() + "'");
    }
}
