package view.tree;

import data.Channel;
import data.DiscordSceneConstants;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sx.blah.discord.handle.obj.IChannel;

public class ChannelItem extends AbstractItem {

    private IChannel channel;

    private ChannelItem(IChannel channel){
        super();
        this.channel = channel;
        node = new ImageView(DiscordSceneConstants.channelIcon);

        MenuItem copyID = new MenuItem("Copy Channel ID");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(channel.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        MenuItem connect = new MenuItem("Connect to");
        connect.setOnAction(event -> {
            Channel.setChannel(channel);
        });

        menu.getItems().add(copyID);
        menu.getItems().add(connect);
    }

    public static DiscordItem of(IChannel channel){
        return new ChannelItem(channel);
    }

    @Override
    public String getName() {
        return channel.getName();
    }
}
