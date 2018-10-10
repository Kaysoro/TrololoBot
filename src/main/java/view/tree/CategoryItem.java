package view.tree;

import data.DiscordRegistry;
import data.DiscordSceneConstants;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class CategoryItem extends AbstractItem {

    private ICategory category;

    private CategoryItem(ICategory category, TreeItem<DiscordItem> tree){
        super(category.getLongID(), tree);
        this.category = category;
        node = new ImageView(DiscordSceneConstants.categoryIcon);

        MenuItem copyID = new MenuItem("Copy the identifier");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(category.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        menu.getItems().add(copyID);
    }

    public static DiscordItem of(ICategory category, TreeItem<DiscordItem> tree){
        return new CategoryItem(category, tree);
    }

    @Override
    public String getName() {
        return category.getName();
    }

    @Override
    public void checkIntegrity() {
        category.getChannels().forEach(channel -> DiscordRegistry.get(IChannel.class, channel.getLongID()).checkIntegrity());
        category.getVoiceChannels().forEach(channel -> DiscordRegistry.get(IVoiceChannel.class, channel.getLongID()).checkIntegrity());
    }

    @Override
    public Class getDiscordClass() {
        return ICategory.class;
    }
}
