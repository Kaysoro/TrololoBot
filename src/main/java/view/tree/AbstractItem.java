package view.tree;

public abstract class AbstractItem implements DiscordItem {

    @Override
    public String toString(){
        return getName();
    }
}
