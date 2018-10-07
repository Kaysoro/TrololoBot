package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.category.CategoryCreateEvent;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class CategoryCreateListener {

    @EventSubscriber
    public void onReady(CategoryCreateEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Category created: "
                + event.getGuild().getName() + " > " + event.getCategory().getName()));
    }
}
