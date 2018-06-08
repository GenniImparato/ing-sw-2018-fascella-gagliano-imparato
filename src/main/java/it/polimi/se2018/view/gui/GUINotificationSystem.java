package it.polimi.se2018.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUINotificationSystem
{
    private GUI              gui;
    private GUIPopup         notification;

    public GUINotificationSystem(GUI gui)
    {
        this.gui = gui;
    }

    private class GUINotificationRemover
    {
        public GUINotificationRemover(GUIPopup notification)
        {
            Timer timer = new Timer();

            TimerTask expireTask = new TimerTask() {
                @Override
                public void run()
                {
                    notification.hide();
                }
            };

            timer.schedule(expireTask, 2000);
        }
    }

    public void showNotification(String message)
    {
        if(notification!= null)
            notification.hide();

        JLabel notificationLabel = new JLabel(message);
        notificationLabel.setFont(new Font("SansSerif", Font.PLAIN, 23));

        notification = new GUIPopup(gui, notificationLabel, gui.getMainWindow().getX() + 10, gui.getMainWindow().getY()+30);
        notification.show();
        new GUINotificationRemover(notification);
    }
}
