package it.polimi.se2018.view.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUINotificationSystem
{
    private GUI                     gui;
    private List<Integer>           positions;
    private List<GUINotification> notifications;
    private final static int        MAX_NOTIFICATIONS = 5;

    public GUINotificationSystem(GUI gui)
    {
        this.gui = gui;
        positions = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    private class GUINotificationRemover
    {
        public GUINotificationRemover(GUINotification notification, Integer position)
        {
            Timer timer = new Timer();

            TimerTask expireTask = new TimerTask() {
                @Override
                public void run()
                {
                    notification.hide();
                    positions.remove(position);
                    notifications.remove(notification);
                }
            };

            timer.schedule(expireTask, 2000);
        }
    }

    public void showNotification(String message)
    {
        Integer currentPosition = 1;

        for(int i=1; i<MAX_NOTIFICATIONS; i++)
        {
            if(!positions.contains(i))
            {
                currentPosition = i;
                break;
            }
        }

        positions.add(currentPosition);

        GUINotification notification = new GUINotification(gui, message, currentPosition);
        notification.show();

        notifications.add(notification);

        new GUINotificationRemover(notification, currentPosition);
    }

    public void removeAllNotifications()
    {
        for(GUINotification notification : notifications)
        {
            notification.hide();
        }

        notifications.clear();
    }
}
