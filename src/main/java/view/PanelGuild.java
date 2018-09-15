package view;

import controllers.ConsultGuildControl;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import util.ClientConfig;
import javax.swing.*;
import java.awt.*;

/**
 * Created by kaysoro on 15/09/2018.
 */

public class PanelGuild extends JPanel {

    public PanelGuild(JLabel title, JComboBox<IChannel> channels) {
        super(new WrapLayout());
        for(IGuild guild : ClientConfig.DISCORD().getGuilds())
            addGuild(guild, title, channels);
    }

    public void addGuild(IGuild guild, JLabel title, JComboBox<IChannel> channels){
        JButton buttonGuild = new JButton(guild.getName());
        buttonGuild.setPreferredSize(new Dimension(250, 30));
        buttonGuild.addActionListener(new ConsultGuildControl(title, channels, guild));
        add(buttonGuild);
    }
}
