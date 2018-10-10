package controllers;

import data.DiscordSecurityUtils;
import javafx.scene.control.TreeItem;
import sx.blah.discord.handle.obj.IGuild;
import util.DiscordClient;
import view.tree.*;

import java.util.function.Predicate;

public class GuildControl {

    private static Predicate<IGuild> isSecured = DiscordSecurityUtils::isSecured;

    public static long getCountVulnerableGuilds() {
        return DiscordClient.DISCORD().getGuilds().stream()
                .filter(isSecured.negate())
                .count();
    }

    public static TreeItem<DiscordItem> createGuild(IGuild guild){
        TreeItem<DiscordItem> guildItem = new TreeItem<>();
        guildItem.setValue(GuildItem.of(guild, guildItem));

        guild.getCategories().forEach(category -> {
                    TreeItem<DiscordItem> categoryItem = new TreeItem<>();
                    categoryItem.setValue(CategoryItem.of(category, categoryItem));

                    category.getChannels().forEach(channel -> {
                        TreeItem<DiscordItem> chanItem = new TreeItem<>();
                        chanItem.setValue(ChannelItem.of(channel, chanItem));
                        categoryItem.getChildren().add(chanItem);
                    });
                    category.getVoiceChannels().forEach(channel -> {
                        TreeItem<DiscordItem> chanItem = new TreeItem<>();
                        chanItem.setValue(VoiceItem.of(channel, chanItem));
                        categoryItem.getChildren().add(chanItem);
                    });

                    guildItem.getChildren().add(categoryItem);
                });

        guild.getChannels().stream()
                .filter(channel -> channel.getCategory() == null)
                .forEach(channel -> {
                    TreeItem<DiscordItem> chanItem = new TreeItem<>();
                    chanItem.setValue(ChannelItem.of(channel, chanItem));
                    guildItem.getChildren().add(chanItem);
                });

        guild.getVoiceChannels().stream()
                .filter(channel -> channel.getCategory() == null)
                .forEach(channel -> {
                    TreeItem<DiscordItem> chanItem = new TreeItem<>();
                    chanItem.setValue(VoiceItem.of(channel, chanItem));
                    guildItem.getChildren().add(chanItem);
                });

        return guildItem;
    }
}
