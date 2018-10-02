package listeners;

import data.DiscordSceneConstants;
import data.DiscordSecurityUtils;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildCreateListener {

    private final static Logger LOG = LoggerFactory.getLogger(GuildCreateListener.class);

    @EventSubscriber
    public void onReady(GuildCreateEvent event) {
        LOG.info("[NEW] " + event.getGuild().getName() + " (" + event.getGuild().getUsers().size() + " users)");
        TreeItem<String> guildItem = new TreeItem<>(event.getGuild().getName(), getImageFor(event.getGuild()));
        for(ICategory category : event.getGuild().getCategories()) {
            TreeItem<String> categoryItem = new TreeItem<>(category.getName(), new ImageView(DiscordSceneConstants.categoryIcon));

            for (IChannel channel : category.getChannels())
                categoryItem.getChildren().add(new TreeItem<>(channel.getName(), new ImageView(DiscordSceneConstants.channelIcon)));
            for (IChannel channel : category.getVoiceChannels())
                categoryItem.getChildren().add(new TreeItem<>(channel.getName(), new ImageView(DiscordSceneConstants.voiceIcon)));

            guildItem.getChildren().add(categoryItem);
        }
        // TODO add to tree
    }

    private ImageView getImageFor(IGuild guild){
        if (DiscordSecurityUtils.isSecured(guild))
            return new ImageView(DiscordSceneConstants.guildsIcon);
        return new ImageView(DiscordSceneConstants.guildnsIcon);
    }
}
