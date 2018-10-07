package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.category.CategoryUpdateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class CategoryUpdateListener {

    @EventSubscriber
    public void onReady(CategoryUpdateEvent event) {
        DiscordRegistry.get(ICategory.class, event.getNewCategory().getLongID()).checkIntegrity();
        Platform.runLater(() -> NotificationControl.updateEvent("Category updated: "
                + event.getGuild().getName() + " > " + event.getNewCategory().getName()));
    }
}
