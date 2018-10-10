package listeners;

import controllers.NotificationControl;
import data.DiscordRegistry;
import javafx.application.Platform;
import javafx.scene.control.TreeItem;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.category.CategoryCreateEvent;
import sx.blah.discord.handle.obj.IGuild;
import view.tree.CategoryItem;
import view.tree.DiscordItem;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class CategoryCreateListener {

    @EventSubscriber
    public void onReady(CategoryCreateEvent event) {
        Platform.runLater(() -> {
            NotificationControl.updateEvent("Category created: "
                    + event.getGuild().getName() + " > " + event.getCategory().getName());
            TreeItem<DiscordItem> categoryItem = new TreeItem<>();
            categoryItem.setValue(CategoryItem.of(event.getCategory(), categoryItem));
            DiscordRegistry.get(IGuild.class, event.getGuild().getLongID())
                    .getTreeItem().getChildren().add(categoryItem);
        });
    }
}
