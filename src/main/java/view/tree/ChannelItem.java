package view.tree;

import javafx.scene.control.MenuItem;
import sx.blah.discord.handle.obj.IChannel;

public class ChannelItem extends AbstractItem {

    private IChannel channel;

    private ChannelItem(IChannel channel){
        super();
        this.channel = channel;
    }

    public static DiscordItem of(IChannel channel){
        return new ChannelItem(channel);
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
