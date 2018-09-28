package util;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class DiscordClient {
    private static IDiscordClient DISCORD;

    /**
     * Connect to discord with the token
     * @param token
     * @throws DiscordException
     */
    public static void connectToDiscord(String token) throws DiscordException {
        if (DISCORD != null && DISCORD.isLoggedIn())
            DISCORD.logout();
        DISCORD = new ClientBuilder()
                .withToken(token)
                .withRecommendedShardCount()
                .login();
    }

    public static IDiscordClient DISCORD() {
        return DISCORD;
    }
}