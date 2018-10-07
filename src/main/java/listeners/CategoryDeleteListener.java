package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.category.CategoryDeleteEvent;

/**
 * Created by kaysoro on 07/10/2018.
 */
public class CategoryDeleteListener {

    @EventSubscriber
    public void onReady(CategoryDeleteEvent event) {
        Platform.runLater(() -> NotificationControl.updateEvent("Category deleted: "
                + event.getGuild().getName() + " > " + event.getCategory().getName()));
    }
}
