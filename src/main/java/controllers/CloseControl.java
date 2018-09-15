package controllers;

import view.Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class CloseControl extends WindowAdapter implements ActionListener {

    private Display display;

    public CloseControl(Display display){
        super();
        this.display = display;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        quit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        quit();
    }

    private void quit(){
        display.dispose();
        System.exit(0);
    }
}
