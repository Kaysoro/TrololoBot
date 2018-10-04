package view.tree;

import javafx.scene.control.MenuItem;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class VoiceItem extends AbstractItem {

    private IVoiceChannel channel;

    private VoiceItem(IVoiceChannel channel){
        super();
        this.channel = channel;
    }

    public static DiscordItem of(IVoiceChannel channel){
        return new VoiceItem(channel);
    }

    @Override
    public String getName() {
        return channel.getName();
    }

    @Override
    public MenuItem getMenusContext() {
        return null;
    }
}
