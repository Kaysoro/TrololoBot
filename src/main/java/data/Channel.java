package data;

import sx.blah.discord.handle.obj.IChannel;

public class Channel {

    private static IChannel channel;

    public static IChannel getChannel() {
        return channel;
    }

    public static void setChannel(IChannel channel) {
        Channel.channel = channel;

        if (channel != null){
            // TODO
        }
    }
}
