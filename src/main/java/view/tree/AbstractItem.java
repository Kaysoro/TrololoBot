package view.tree;

import data.DiscordRegistry;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;

public abstract class AbstractItem implements DiscordItem {

    ContextMenu menu;
    Node node;
    Tooltip tooltip;
    private TreeItem<DiscordItem> treeItem;

    AbstractItem(long id, TreeItem<DiscordItem> tree){
        super();
        treeItem = tree;
        menu = new ContextMenu();
        DiscordRegistry.put(this, id);
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public ContextMenu getMenu() {
        return menu;
    }

    @Override
    public Tooltip getToolTip() { return tooltip; }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public TreeItem<DiscordItem> getTreeItem() {
        return treeItem;
    }
}
