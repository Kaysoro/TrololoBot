package controllers;

import view.UsernameDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steve on 28/09/2016.
 */
public class UsernameControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new UsernameDialog().setVisible(true);
    }
}
