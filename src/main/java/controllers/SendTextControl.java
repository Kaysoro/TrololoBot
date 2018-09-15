package controllers;

import data.Channel;
import util.Message;
import view.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class SendTextControl implements ActionListener {

    private JTextArea text;

    public SendTextControl(JTextArea text) {
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Channel.getChannel() != null && ! text.getText().isEmpty()){
            Message.sendText(Channel.getChannel(), text.getText());
            Display.getInstance().getChat().setText(Display.getInstance().getChat().getText() + "\n"
                + "TROLOLO : " + text.getText());
            text.setText("");
            // TODO beautify this component
        }
    }
}