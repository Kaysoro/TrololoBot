package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.role.RoleEvent;
import sx.blah.discord.handle.obj.IGuild;

/**
 * Created by kaysoro on 06/10/2018.
 */
public class RoleListener {

    @EventSubscriber
    public void onReady(RoleEvent event) {
        DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).checkIntegrity();
        Platform.runLater(() -> NotificationControl.updateEvent("Role event catched: "
            + event.getGuild().getName() + " > " + event.getRole().getName()));
    }
}
