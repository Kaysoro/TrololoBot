package controllers;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;
import view.TrololoBot;

import java.util.EnumSet;

import static sx.blah.discord.handle.obj.Permissions.ATTACH_FILES;
import static sx.blah.discord.handle.obj.Permissions.READ_MESSAGES;
import static sx.blah.discord.handle.obj.Permissions.SEND_MESSAGES;

public class ChannelControl {

    private static IChannel channel;

    public static IChannel getChannel() {
        return channel;
    }

    public static void setChannel(IChannel channel) {
        //  TODO clear panel messages
        ChannelControl.channel = channel;
        checkActionChannel(channel);
    }

    public static void checkActionChannel(IChannel channel){
        if (channel != null){
            boolean sendMessage = PermissionUtils.hasPermissions(channel, DiscordClient.DISCORD().getOurUser(),
                    EnumSet.of(READ_MESSAGES, SEND_MESSAGES));
            boolean attachFile = PermissionUtils.hasPermissions(channel, DiscordClient.DISCORD().getOurUser(),
                    EnumSet.of(READ_MESSAGES, SEND_MESSAGES, ATTACH_FILES));
            TrololoBot.getStage().getScene().lookup("#myTextArea").setDisable(! sendMessage);
            TrololoBot.getStage().getScene().lookup("#sendText").setDisable(! sendMessage);

            TrololoBot.getStage().getScene().lookup("#sendEmbed").setDisable(! attachFile);
            TrololoBot.getStage().getScene().lookup("#sendImage").setDisable(! attachFile);
            TrololoBot.getStage().getScene().lookup("#sendFile").setDisable(! attachFile);

            // TODO Download 20 last messages if possible
        }
        else {
            TrololoBot.getStage().getScene().lookup("#myTextArea").setDisable(true);
            TrololoBot.getStage().getScene().lookup("#sendText").setDisable(true);

            TrololoBot.getStage().getScene().lookup("#sendEmbed").setDisable(true);
            TrololoBot.getStage().getScene().lookup("#sendImage").setDisable(true);
            TrololoBot.getStage().getScene().lookup("#sendFile").setDisable(true);
        }
    }
}
