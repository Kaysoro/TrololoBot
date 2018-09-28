package listeners;

import controllers.NotificationControl;
import data.DiscordSceneConstants;
import javafx.application.Platform;
import javafx.scene.Scene;
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
public class ReadyListener extends SceneLinkedListener {
    private final static Logger LOG = LoggerFactory.getLogger(ReadyListener.class);

    public ReadyListener(Scene scene){
        super(scene);
    }

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        long time = Instant.now().toEpochMilli();

        LOG.info("Displaying Discord listeners...");

        Platform.runLater(() -> {
            TreeItem<String> rootNode = new TreeItem<>(event.getClient().getApplicationName(), new ImageView(DiscordSceneConstants.robotIcon));
            rootNode.setExpanded(true);
            TreeView<String> tree = ((TreeView<String>) getScene().lookup("#tree"));

            for(IGuild guild : event.getClient().getGuilds()){
                TreeItem<String> guildItem = new TreeItem<>(guild.getName(), new ImageView(DiscordSceneConstants.guildIcon));
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
            NotificationControl.updateGuildsNumber(getScene());
            NotificationControl.connected(getScene());
        });


        LOG.info("Adding Discord listeners...");
        DiscordClient.DISCORD().getDispatcher().registerListener(new GuildCreateListener(getScene()));
        DiscordClient.DISCORD().getDispatcher().registerListener(new GuildLeaveListener(getScene()));

        LOG.info("Listening Discord messages...");
        DiscordClient.DISCORD().getDispatcher().registerListener(new MessageReceivedListener(getScene()));
        DiscordClient.DISCORD().getDispatcher().registerListener(new MessageSendListener(getScene()));

        LOG.info("UP in " + (Instant.now().toEpochMilli() - time) + "ms");
    }
}
