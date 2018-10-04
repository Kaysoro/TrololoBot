package view.tree;

import javafx.scene.control.MenuItem;

public interface DiscordItem {

    /**
     * @return name displayed into the tree
     */
    String getName();

    /**
     *
     * @return Menu dedicated to the cell
     */
    MenuItem getMenusContext();
}
