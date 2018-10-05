package view.tree;

import data.DiscordSceneConstants;
import javafx.scene.image.ImageView;
import sx.blah.discord.handle.obj.ICategory;

public class CategoryItem extends AbstractItem {

    private ICategory category;

    private CategoryItem(ICategory category){
        super();
        this.category = category;
        node = new ImageView(DiscordSceneConstants.categoryIcon);
    }

    public static DiscordItem of(ICategory category){
        return new CategoryItem(category);
    }

    @Override
    public String getName() {
        return category.getName();
    }
}
