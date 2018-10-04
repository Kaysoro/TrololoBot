package view.tree;

import javafx.scene.control.MenuItem;
import sx.blah.discord.handle.obj.ICategory;

public class CategoryItem extends AbstractItem {

    private ICategory category;

    private CategoryItem(ICategory category){
        super();
        this.category = category;
    }

    public static DiscordItem of(ICategory category){
        return new CategoryItem(category);
    }

    @Override
    public String getName() {
        return category.getName();
    }

    @Override
    public MenuItem getMenusContext() {
        return null;
    }
}
