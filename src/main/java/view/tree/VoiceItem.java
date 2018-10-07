package view.tree;

import data.DiscordSceneConstants;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class VoiceItem extends AbstractItem {

    private IVoiceChannel channel;

    private VoiceItem(IVoiceChannel channel, TreeItem<DiscordItem> tree){
        super(channel.getLongID(), tree);
        this.channel = channel;
        node = new ImageView(DiscordSceneConstants.voiceIcon);

        MenuItem copyID = new MenuItem("Copy the identifier");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(channel.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        menu.getItems().add(copyID);
    }

    public static DiscordItem of(IVoiceChannel channel, TreeItem<DiscordItem> tree){
        return new VoiceItem(channel, tree);
    }

    @Override
    public String getName() {
        return channel.getName() + " (" + channel.getConnectedUsers().size() + "/" + channel.getUserLimit() + ")";
    }

    @Override
    public void checkIntegrity() {
        // TODO authorize voice connexion or not
    }

    @Override
    public Class getDiscordClass() {
        return IVoiceChannel.class;
    }
}
