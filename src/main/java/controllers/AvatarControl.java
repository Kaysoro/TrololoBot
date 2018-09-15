package controllers;

import sx.blah.discord.util.Image;
import util.ClientConfig;
import view.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steve on 28/09/2016.
 */
public class AvatarControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(Display.getInstance()) == JFileChooser.APPROVE_OPTION
                && fileChooser.getSelectedFile().exists())
            ClientConfig.DISCORD().changeAvatar(Image.forFile(fileChooser.getSelectedFile()));
    }
}