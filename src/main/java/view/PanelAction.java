package view;

import sx.blah.discord.handle.obj.IChannel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kaysoro on 15/09/2018.
 */

public class PanelAction extends JPanel {


    public PanelAction(JLabel title, JComboBox<IChannel> channels) {
        super(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(channels, BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // TODO Add actions
    }
}
