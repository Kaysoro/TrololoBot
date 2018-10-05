package view.tree;

import data.DiscordSceneConstants;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import sx.blah.discord.api.IDiscordClient;

public class BotItem extends AbstractItem {

    private IDiscordClient client;

    private BotItem(IDiscordClient client){
        super();
        this.client = client;
        node = new ImageView(DiscordSceneConstants.robotIcon);
    }

    public static DiscordItem of(IDiscordClient client){
        return new BotItem(client);
    }

    @Override
    public String getName() {
        return client.getApplicationName();
    }

    @Override
    public ContextMenu getMenu() {
        return null;
    }
}
