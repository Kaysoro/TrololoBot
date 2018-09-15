package controllers;

import data.Channel;
import sx.blah.discord.handle.obj.IChannel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class SelectChannelControl implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
        Channel.setChannel((IChannel) e.getItem());
    }
}