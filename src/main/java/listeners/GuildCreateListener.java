package listeners;

import data.DiscordSceneConstants;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildCreateListener extends SceneLinkedListener {

    private final static Logger LOG = LoggerFactory.getLogger(GuildCreateListener.class);

    public GuildCreateListener(Scene scene){
        super(scene);
    }

    @EventSubscriber
    public void onReady(GuildCreateEvent event) {
        LOG.info("[NEW] " + event.getGuild().getName() + " (" + event.getGuild().getUsers().size() + " users)");
        TreeItem<String> guildItem = new TreeItem<>(event.getGuild().getName(), new ImageView(DiscordSceneConstants.guildIcon));
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
}
