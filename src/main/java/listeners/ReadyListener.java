package listeners;

import controllers.NotificationControl;
import data.DiscordSceneConstants;
import data.DiscordSecurityUtils;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import util.DiscordClient;

import java.time.Instant;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class ReadyListener {
    private final static Logger LOG = LoggerFactory.getLogger(ReadyListener.class);

    private TreeView<String> tree;

    private MenuItem usernameMenuItem;

    private MenuItem avatarMenuItem;

    private MenuItem dispatcherMenuItem;

    public ReadyListener(TreeView<String> tree, MenuItem username, MenuItem avatar, MenuItem dispatcher){
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
            TreeItem<String> rootNode = new TreeItem<>(event.getClient().getApplicationName(), new ImageView(DiscordSceneConstants.robotIcon));
            rootNode.setExpanded(true);
            int vulnerableGuilds = 0;

            for(IGuild guild : event.getClient().getGuilds()){
                TreeItem<String> guildItem = new TreeItem<>(guild.getName(), getImageFor(guild));
                for(ICategory category : guild.getCategories()) {
                    TreeItem<String> categoryItem = new TreeItem<>(category.getName(), new ImageView(DiscordSceneConstants.categoryIcon));
                    for(IChannel channel : category.getChannels()) {
                        TreeItem<String> chanItem = new TreeItem<>(channel.getName(), new ImageView(DiscordSceneConstants.channelIcon));

                        categoryItem.getChildren().add(chanItem);
                    }
                    for(IChannel channel : category.getVoiceChannels())
                        categoryItem.getChildren().add(new TreeItem<>(channel.getName(), new ImageView(DiscordSceneConstants.voiceIcon)));

                    guildItem.getChildren().add(categoryItem);
                }
                rootNode.getChildren().add(guildItem);
            }
            tree.setRoot(rootNode);
            NotificationControl.updateGuildsNumber(event.getClient().getGuilds().size(), vulnerableGuilds);
            NotificationControl.connected();

            usernameMenuItem.setDisable(false);
            avatarMenuItem.setDisable(false);
            dispatcherMenuItem.setDisable(false);
        });


        LOG.info("Adding Discord listeners...");
        DiscordClient.DISCORD().getDispatcher().registerListener(new GuildCreateListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new GuildLeaveListener());

        LOG.info("Listening Discord messages...");
        DiscordClient.DISCORD().getDispatcher().registerListener(new MessageReceivedListener());
        DiscordClient.DISCORD().getDispatcher().registerListener(new MessageSendListener());

        LOG.info("UP in " + (Instant.now().toEpochMilli() - time) + "ms");
    }

    private ImageView getImageFor(IGuild guild){
        if (DiscordSecurityUtils.isSecured(guild))
            return new ImageView(DiscordSceneConstants.guildsIcon);
        return new ImageView(DiscordSceneConstants.guildnsIcon);
    }
}
