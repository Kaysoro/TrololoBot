package controllers;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;
import view.TrololoBot;

import java.util.EnumSet;

import static sx.blah.discord.handle.obj.Permissions.*;

public class ChannelControl {

    private static final int MESSAGE_HISTORY_LIMIT = 20;

    private static IChannel channel;

    public static IChannel getChannel() {
        return channel;
    }

    public static void setChannel(IChannel channel) {
        //  TODO clear panel messages
        ChannelControl.channel = channel;
        if (channel != null && PermissionUtils.hasPermissions(channel, DiscordClient.DISCORD().getOurUser(),
                EnumSet.of(READ_MESSAGES, READ_MESSAGE_HISTORY))){
            MessageHistory history = channel.getMessageHistory(MESSAGE_HISTORY_LIMIT);
            history.forEach(System.out::println);
            // TODO Download 20 last messages if possible
        }
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
