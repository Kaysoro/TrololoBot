package view.tree;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;

public abstract class AbstractItem implements DiscordItem {

    ContextMenu menu;
    Node node;

    AbstractItem(){
        super();
        menu = new ContextMenu();
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
    public Node getNode() {
        return node;
    }
}
