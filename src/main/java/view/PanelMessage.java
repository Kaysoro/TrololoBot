package view;

import controllers.SendFileControl;
import controllers.SendImageControl;
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
        setPreferredSize(new Dimension(1, 100));
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setAutoscrolls(true);

        JScrollPane scrollText = new JScrollPane(text);
        scrollText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton sendText = new JButton("Text");
        sendText.addActionListener(new SendTextControl(text));
        JButton sendFile = new JButton("File");
        sendFile.addActionListener(new SendFileControl());
        JButton sendImage = new JButton("Image");
        sendImage.addActionListener(new SendImageControl());

        JPanel sendActions = new JPanel();
        sendActions.add(sendText);
        sendActions.add(sendFile);
        sendActions.add(sendImage);

        add(scrollText, BorderLayout.CENTER);
        add(sendActions, BorderLayout.EAST);
    }
}
