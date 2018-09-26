package controllers;

import data.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class SendImageControl implements ActionListener {

    private final static Logger LOG = LoggerFactory.getLogger(SendImageControl.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Channel.getChannel() != null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
            fileChooser.setAcceptAllFileFilterUsed(false);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION
                    && fileChooser.getSelectedFile().exists())
                try {
                    Message.sendImage(Channel.getChannel(),
                            ImageIO.read(fileChooser.getSelectedFile()), fileChooser.getSelectedFile().getName());
                } catch (IOException e1){
                    LOG.error("actionPerformed", e1);
                }
        }
    }
}