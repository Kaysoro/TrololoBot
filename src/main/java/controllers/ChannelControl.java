package controllers;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IDiscordObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;
import util.DiscordViewUtils;
import view.TrololoBot;
import java.util.Comparator;
import java.util.EnumSet;

import static sx.blah.discord.handle.obj.Permissions.*;

public class ChannelControl {

    private static final int MESSAGE_HISTORY_LIMIT = 20;

    private static IChannel channel;

    public static IChannel getChannel() {
        return channel;
    }

    public static void setChannel(IChannel channel) {
        VBox panelMessages = (VBox) TrololoBot.getStage().getScene().lookup("#messages");
        panelMessages.getChildren().clear();
        ChannelControl.channel = channel;
        if (channel != null && PermissionUtils.hasPermissions(channel, DiscordClient.DISCORD().getOurUser(),
                EnumSet.of(READ_MESSAGES, READ_MESSAGE_HISTORY))){
            channel.getMessageHistory(MESSAGE_HISTORY_LIMIT).stream()
                    .sorted(Comparator.comparing(IDiscordObject::getCreationDate))
                    .forEach(message -> panelMessages.getChildren().add(DiscordViewUtils.getMessageView(message)));
            ScrollPane scrollPane = ((ScrollPane) TrololoBot.getStage().getScene().lookup("#scrollMessages"));
            scrollPane.setVvalue(scrollPane.getVmax());
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

    public static void manageMessage(IMessage message){
        if (getChannel() != null && message.getChannel().getLongID() == getChannel().getLongID()) {
            ((VBox) TrololoBot.getStage().getScene().lookup("#messages")).getChildren()
                    .add(DiscordViewUtils.getMessageView(message));
            ScrollPane scrollPane = ((ScrollPane) TrololoBot.getStage().getScene().lookup("#scrollMessages"));
            scrollPane.setVvalue(scrollPane.getVmax());
        }
    }
}
