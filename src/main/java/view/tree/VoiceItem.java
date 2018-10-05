package view.tree;

import data.DiscordSceneConstants;
import javafx.scene.image.ImageView;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class VoiceItem extends AbstractItem {

    private IVoiceChannel channel;

    private VoiceItem(IVoiceChannel channel){
        super();
        this.channel = channel;
        node = new ImageView(DiscordSceneConstants.voiceIcon);
    }

    public static DiscordItem of(IVoiceChannel channel){
        return new VoiceItem(channel);
    }

    @Override
    public String getName() {
        return channel.getName();
    }
}
