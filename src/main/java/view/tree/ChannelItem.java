package view.tree;

import controllers.ExceptionControl;
import data.Channel;
import data.DiscordSceneConstants;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IInvite;
import sx.blah.discord.handle.obj.Permissions;
import util.DiscordClient;

public class ChannelItem extends AbstractItem {

    private IChannel channel;
    private MenuItem connect;
    private MenuItem createInvite;

    private ChannelItem(IChannel channel, TreeItem<DiscordItem> tree){
        super(channel.getLongID(), tree);
        this.channel = channel;
        node = new ImageView(DiscordSceneConstants.channelIcon);

        connect = new MenuItem("Connect to chat");
        connect.setOnAction(event -> Channel.setChannel(channel));
        connect.setDisable(! channel.getModifiedPermissions(DiscordClient.DISCORD().getOurUser())
                .contains(Permissions.READ_MESSAGES));

        createInvite = new MenuItem("Create invite");
        createInvite.setDisable(! channel.getModifiedPermissions(DiscordClient.DISCORD().getOurUser())
                .contains(Permissions.CREATE_INVITE));
        createInvite.setOnAction(event -> {
            try {
                IInvite invite = channel.createInvite(0, 0, true, false);
                ClipboardContent content = new ClipboardContent();
                content.putString(invite.getCode());
                Clipboard.getSystemClipboard().setContent(content);
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Create invite");
                    alert.setHeaderText(null);
                    alert.setContentText("Invite created and available in clipboard: " + invite.getCode());
                    alert.showAndWait();
                });
            } catch (Exception e){
                ExceptionControl.throwException("Create invite", e);
            }
        });

        MenuItem copyID = new MenuItem("Copy the identifier");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(channel.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        menu.getItems().addAll(connect, createInvite, new SeparatorMenuItem(), copyID);
    }

    public static DiscordItem of(IChannel channel, TreeItem<DiscordItem> tree){
        return new ChannelItem(channel, tree);
    }

    @Override
    public String getName() {
        return channel.getName();
    }

    @Override
    public void checkIntegrity() {
        connect.setDisable(! channel.getModifiedPermissions(DiscordClient.DISCORD().getOurUser())
                .contains(Permissions.READ_MESSAGES));
        createInvite.setDisable(! channel.getModifiedPermissions(DiscordClient.DISCORD().getOurUser())
                .contains(Permissions.CREATE_INVITE));
    }

    @Override
    public Class getDiscordClass() {
        return IChannel.class;
    }
}
