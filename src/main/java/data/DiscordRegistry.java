package data;

import view.tree.DiscordItem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiscordRegistry {

    private static Map<String, DiscordItem> registry = new ConcurrentHashMap<>();

    public static DiscordItem get(Class discordClass, long id){
        return registry.get(getKey(discordClass, id));
    }

    public static void put(DiscordItem item, long id){
        registry.put(getKey(item.getDiscordClass(), id), item);
    }

    public static void remove(Class discordClass, long id){
        registry.remove(getKey(discordClass, id));
    }

    private static String getKey(Class discordClass, long id){
        return discordClass.getName() + ":" + id;
    }
}
