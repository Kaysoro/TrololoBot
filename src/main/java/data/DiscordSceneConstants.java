package data;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiscordSceneConstants {

    // About
    public static final Node aboutIcon = new ImageView(new Image(DiscordSceneConstants.class.getResourceAsStream("../images/github.png")));
    // Tree
    public static final Image robotIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/discord.png"));
    public static final Image guildSecuredIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/guildSecured.png"));
    public static final Image guildDangerousIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/guildDangerous.png"));
    public static final Image guildCriticalIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/guildCritical.png"));
    public static final Image categoryIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/category.png"));
    public static final Image channelIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/channel.png"));
    public static final Image voiceIcon = new Image(DiscordSceneConstants.class.getResourceAsStream("../images/voice.png"));

    // Notification bar
    public static final Node connectingIcon = new ImageView(new Image(DiscordSceneConstants.class.getResourceAsStream("../images/connecting.png")));
    public static final Node connectedIcon = new ImageView(new Image(DiscordSceneConstants.class.getResourceAsStream("../images/connected.png")));
    public static final Node disconnectedIcon = new ImageView(new Image(DiscordSceneConstants.class.getResourceAsStream("../images/disconnected.png")));
}
