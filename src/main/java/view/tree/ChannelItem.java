package view.tree;

import data.Channel;
import data.DiscordSceneConstants;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sx.blah.discord.handle.obj.IChannel;

public class ChannelItem extends AbstractItem {

    private IChannel channel;

    private ChannelItem(IChannel channel, TreeItem<DiscordItem> tree){
        super(channel.getLongID(), tree);
        this.channel = channel;
        node = new ImageView(DiscordSceneConstants.channelIcon);

        MenuItem connect = new MenuItem("Connect to chat");
        connect.setOnAction(event -> {
            Channel.setChannel(channel);
        });

        MenuItem copyID = new MenuItem("Copy the identifier");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(channel.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        menu.getItems().addAll(connect, copyID);
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
        // TODO authorize connect chat or not
    }

    @Override
    public Class getDiscordClass() {
        return IChannel.class;
    }
}
