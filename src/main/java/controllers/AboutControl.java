package controllers;

import view.AboutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steve on 28/09/2016.
 */
public class AboutControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new AboutDialog().setVisible(true);
    }
}
