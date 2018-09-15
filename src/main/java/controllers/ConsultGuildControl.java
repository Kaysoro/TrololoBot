package controllers;

import data.ChannelDecorator;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import view.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class ConsultGuildControl implements ActionListener {

    private IGuild guild;
    private JLabel title;
    private JComboBox<IChannel> channels;

    public ConsultGuildControl(JLabel title, JComboBox<IChannel> channels, IGuild guild){
        super();
        this.title = title;
        this.guild = guild;
        this.channels = channels;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        title.setText(guild.getName());
        title.setBackground(new Color(0, 180, 0));
        channels.removeAllItems();
        for(IChannel channel : guild.getChannels())
            channels.addItem(new ChannelDecorator(channel));
        Display.getInstance().getChat().setText("");
    }
}