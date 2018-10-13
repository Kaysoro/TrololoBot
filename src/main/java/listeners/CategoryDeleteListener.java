package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.category.CategoryDeleteEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IGuild;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class CategoryDeleteListener {

    @EventSubscriber
    public void onReady(CategoryDeleteEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Category deleted: "
                    + event.getGuild().getName() + " > " + event.getCategory().getName());
            DiscordRegistry.get(IGuild.class, event.getGuild().getLongID()).getTreeItem().getChildren()
                    .remove(DiscordRegistry.get(ICategory.class, event.getCategory().getLongID()).getTreeItem());
            DiscordRegistry.remove(ICategory.class, event.getCategory().getLongID());
        });
    }
}
