package view;

import controllers.SendTextControl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kaysoro on 15/09/2018.
 */

public class PanelMessage extends JPanel {

    public PanelMessage() {
        super(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea text = new JTextArea();
        JButton sendText = new JButton("Text");
        sendText.addActionListener(new SendTextControl(text));
        JButton sendFile = new JButton("File");
        // TODO listener
        JButton sendImage = new JButton("Image");

        JPanel sendActions = new JPanel();
        sendActions.add(sendText);
        sendActions.add(sendFile);
        sendActions.add(sendImage);

        add(text, BorderLayout.CENTER);
        add(sendActions, BorderLayout.EAST);
    }
}
