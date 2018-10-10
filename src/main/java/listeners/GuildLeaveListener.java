package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import javafx.scene.control.TreeView;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildLeaveEvent;
import sx.blah.discord.handle.obj.IGuild;
import view.TrololoBot;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildLeaveListener {

    @EventSubscriber
    public void onReady(GuildLeaveEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Guild losed: " + event.getGuild().getName());
            ((TreeView<DiscordItem>) TrololoBot.getStage().getScene().lookup("#tree"))
                    .getRoot().getChildren().remove(
                            DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).getTreeItem());
        });
        DiscordRegistry.remove(IGuild.class, event.getGuild().getLongID());
    }
}
