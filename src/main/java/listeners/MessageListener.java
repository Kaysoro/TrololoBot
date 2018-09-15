package listeners;

import data.Channel;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import view.Display;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class MessageListener {

    @EventSubscriber
    public void onReady(MessageReceivedEvent event) {
        if (Channel.getChannel() != null && event.getChannel().getLongID() == Channel.getChannel().getLongID()){
            Display.getInstance().getChat().setText(Display.getInstance().getChat().getText() + "\n"
            + event.getAuthor().getName() + " : " + event.getMessage().getContent());
            //TODO display message in PanelHistory
        }
    }
}
