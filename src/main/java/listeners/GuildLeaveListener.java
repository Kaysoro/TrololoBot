package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildLeaveEvent;
import view.Display;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildLeaveListener {

    private final static Logger LOG = LoggerFactory.getLogger(GuildLeaveListener.class);

    @EventSubscriber
    public void onReady(GuildLeaveEvent event) {
        LOG.info("[LOSE] **" + event.getGuild().getName() + "** (" + event.getGuild().getUsers().size() +  " users)");
        Display.getInstance().removeGuild(event.getGuild());
    }
}