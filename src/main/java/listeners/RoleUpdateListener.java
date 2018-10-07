package listeners;

import data.DiscordRegistry;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.role.RoleUpdateEvent;
import sx.blah.discord.handle.obj.IGuild;

/**
 * Created by kaysoro on 06/10/2018.
 */
public class RoleUpdateListener {

    @EventSubscriber
    public void onReady(RoleUpdateEvent event) {
        DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).checkIntegrity();
    }
}
