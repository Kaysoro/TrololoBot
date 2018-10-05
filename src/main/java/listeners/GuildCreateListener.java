package listeners;

import javafx.scene.control.TreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;
import view.tree.*;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class GuildCreateListener {

    private final static Logger LOG = LoggerFactory.getLogger(GuildCreateListener.class);

    @EventSubscriber
    public void onReady(GuildCreateEvent event) {
        LOG.info("[NEW] " + event.getGuild().getName() + " (" + event.getGuild().getUsers().size() + " users)");
        TreeItem<DiscordItem> guildItem = new TreeItem<>(GuildItem.of(event.getGuild()));
        for(ICategory category : event.getGuild().getCategories()) {
            TreeItem<DiscordItem> categoryItem = new TreeItem<>(CategoryItem.of(category));

            for (IChannel channel : category.getChannels())
                categoryItem.getChildren().add(new TreeItem<>(ChannelItem.of(channel)));
            for (IVoiceChannel channel : category.getVoiceChannels())
                categoryItem.getChildren().add(new TreeItem<>(VoiceItem.of(channel)));

            guildItem.getChildren().add(categoryItem);
        }
        // TODO add to tree
    }
}
