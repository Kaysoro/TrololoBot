package view.tree;

import javafx.scene.control.MenuItem;
import sx.blah.discord.api.IDiscordClient;

public class BotItem extends AbstractItem {

    private IDiscordClient client;

    private BotItem(IDiscordClient client){
        super();
        this.client = client;
    }

    public static DiscordItem of(IDiscordClient client){
        return new BotItem(client);
    }

    @Override
    public String getName() {
        return client.getApplicationName();
    }

    @Override
    public MenuItem getMenusContext() {
        return null;
    }
}
