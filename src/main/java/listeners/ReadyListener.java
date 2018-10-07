package listeners;

import controllers.NotificationControl;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;
import util.DiscordClient;
import view.tree.*;

import java.time.Instant;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class ReadyListener {
    private final static Logger LOG = LoggerFactory.getLogger(ReadyListener.class);

    private TreeView<DiscordItem> tree;

    private MenuItem usernameMenuItem;

    private MenuItem avatarMenuItem;

    private MenuItem dispatcherMenuItem;

    public ReadyListener(TreeView<DiscordItem> tree, MenuItem username, MenuItem avatar, MenuItem dispatcher){
        this.tree = tree;
        this.usernameMenuItem = username;
        this.avatarMenuItem = avatar;
        this.dispatcherMenuItem = dispatcher;
    }

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        long time = Instant.now().toEpochMilli();

        LOG.info("Displaying Discord listeners...");

        Platform.runLater(() -> {
            TreeItem<DiscordItem> rootNode = new TreeItem<>();
            rootNode.setValue(BotItem.of(event.getClient(), rootNode));
            rootNode.setExpanded(true);

            for(IGuild guild : event.getClient().getGuilds()){
                TreeItem<DiscordItem> guildItem = new TreeItem<>();
                guildItem.setValue(GuildItem.of(guild, guildItem));
                for(ICategory category : guild.getCategories()) {
                    TreeItem<DiscordItem> categoryItem = new TreeItem<>();
                    categoryItem.setValue(CategoryItem.of(category, guildItem));
                    for(IChannel channel : category.getChannels()) {
                        TreeItem<DiscordItem> chanItem = new TreeItem<>();
                        chanItem.setValue(ChannelItem.of(channel, chanItem));
                        categoryItem.getChildren().add(chanItem);
                    }
                    for(IVoiceChannel channel : category.getVoiceChannels()) {
                        TreeItem<DiscordItem> voiceItem = new TreeItem<>();
                        voiceItem.setValue(VoiceItem.of(channel, voiceItem));
                        categoryItem.getChildren().add(voiceItem);
                    }

                    guildItem.getChildren().add(categoryItem);
                }
                rootNode.getChildren().add(guildItem);
            }
            tree.setRoot(rootNode);
            NotificationControl.updateGuildsNumber();
            NotificationControl.connected();

            usernameMenuItem.setDisable(false);
            avatarMenuItem.setDisable(false);
            dispatcherMenuItem.setDisable(false);
        });


        LOG.info("Adding Discord listeners...");
        // Guild listeners
        DiscordClient.DISCORD().getDispatcher().registerListener(new GuildCreateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new GuildLeaveListener());

        // Role listeners
        DiscordClient.DISCORD().getDispatcher().registerListener(new RoleListener());

        // Category listeners
        DiscordClient.DISCORD().getDispatcher().registerListener(new CategoryCreateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new CategoryUpdateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new CategoryDeleteListener());

        // Channel listeners
        DiscordClient.DISCORD().getDispatcher().registerListener(new ChannelCreateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new ChannelUpdateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new ChannelDeleteListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new ChannelCategoryUpdateListener());

        // Voice channel listeners
        DiscordClient.DISCORD().getDispatcher().registerListener(new VoiceChannelCreateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new VoiceChannelUpdateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new VoiceChannelDeleteListener());

        LOG.info("Listening Discord messages...");
        DiscordClient.DISCORD().getDispatcher().registerListener(new MessageReceivedListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new MessageSendListener());

        LOG.info("UP in " + (Instant.now().toEpochMilli() - time) + "ms");
    }
}
