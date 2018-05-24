package it.polimi.se2018.view.gui;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class GUINotification extends Popup {
    private GUI gui;

    public GUINotification(GUI gui, String message) {
        super(gui.getMainWindow(),
                new JLabel(message),
                gui.getMainWindow().getX() + 10,
                gui.getMainWindow().getY() + 30);

        show();

        Timer timer = new Timer();

        TimerTask expireTask = new TimerTask() {
            @Override
            public void run() {
                hide();
            }
        };
        timer.schedule(expireTask, 2000);
    }
}

