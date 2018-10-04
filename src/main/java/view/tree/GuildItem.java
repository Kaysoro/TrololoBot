package view.tree;

import javafx.scene.control.MenuItem;
import sx.blah.discord.handle.obj.IGuild;

public class GuildItem extends AbstractItem {

    private IGuild guild;

    private GuildItem(IGuild guild){
        super();
        this.guild = guild;
    }

    public static DiscordItem of(IGuild guild){
        return new GuildItem(guild);
    }

    @Override
    public String getName() {
        return guild.getName();
    }

    @Override
    public MenuItem getMenusContext() {
        return null;
    }
}
