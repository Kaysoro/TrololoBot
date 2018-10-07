package view.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

public interface DiscordItem {

    /**
     * @return name displayed into the tree
     */
    String getName();

    /**
     *
     * @return Menu dedicated to the cell
     */
    ContextMenu getMenu();

    /**
     *
     * @return Icon for treeItem
     */
    Node getNode();

    /**
     * @return tree item
     */
    TreeItem<DiscordItem> getTreeItem();

    /**
     * enable/disable menus in the current context
     */
    void checkIntegrity();

    /**
     * @return Class represented by the item
     */
    Class getDiscordClass();
}
