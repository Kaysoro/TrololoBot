package controllers;

import data.DiscordSecurityUtils;
import sx.blah.discord.handle.obj.IGuild;
import util.DiscordClient;

import java.util.function.Predicate;

public class GuildControl {

    private static Predicate<IGuild> isSecured = DiscordSecurityUtils::isSecured;

    public static long getCountVulnerableGuilds() {
        return DiscordClient.DISCORD().getGuilds().stream()
                .filter(isSecured.negate())
                .count();
    }
}
