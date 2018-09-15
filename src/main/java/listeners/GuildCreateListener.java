package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import view.Display;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildCreateListener {

    private final static Logger LOG = LoggerFactory.getLogger(GuildCreateListener.class);

    public GuildCreateListener(){
        super();
    }

    @EventSubscriber
    public void onReady(GuildCreateEvent event) {
        LOG.info("[NEW] " + event.getGuild().getName() + " (" + event.getGuild().getUsers().size() + " users)");
        Display.getInstance().addGuild(event.getGuild());
    }
}
