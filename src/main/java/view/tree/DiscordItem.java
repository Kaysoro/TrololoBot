package view.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;

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
}
