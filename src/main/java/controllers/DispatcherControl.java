package controllers;

import view.DispatcherDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steve on 28/09/2016.
 */
public class DispatcherControl implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new DispatcherDialog().setVisible(true);
    }
}
