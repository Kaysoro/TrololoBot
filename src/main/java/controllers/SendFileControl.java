package controllers;

import data.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Message;
import view.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class SendFileControl implements ActionListener {

    private final static Logger LOG = LoggerFactory.getLogger(SendFileControl.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Channel.getChannel() != null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);

            if (fileChooser.showOpenDialog(Display.getInstance()) == JFileChooser.APPROVE_OPTION
                    && fileChooser.getSelectedFile().exists())
                try {
                    Message.sendFile(Channel.getChannel(),
                            new FileInputStream(fileChooser.getSelectedFile()), fileChooser.getSelectedFile().getName());
                } catch (IOException e1){
                    LOG.error("actionPerformed", e1);
                }
        }
    }
}