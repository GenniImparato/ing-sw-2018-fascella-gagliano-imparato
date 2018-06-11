package it.polimi.se2018.view.gui;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.NotificationMessageParser;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.views.CLIView;
import it.polimi.se2018.view.gui.dialogs.GUIDrawDieDialog;
import it.polimi.se2018.view.gui.dialogs.GUIOKDialog;
import it.polimi.se2018.view.gui.views.GUIGameView;
import it.polimi.se2018.view.gui.views.GUILobbyView;
import it.polimi.se2018.view.gui.views.GUIMenuView;
import it.polimi.se2018.view.gui.views.GUIView;

import javax.swing.*;
import java.awt.*;

public class GUI extends View
{
    private transient GUIView                   currentView;
    private transient JFrame                    mainWindow;
    private transient String                    serverIp;
    private transient GUIMessageParser          parser;
    private transient GUINotificationSystem     notifications;
    private transient JPanel                    glassPanel;
    private transient GUI                       thisElement;

    public GUI()
    {
        thisElement = this;
        notifications = new GUINotificationSystem(this);

        mainWindow = new JFrame("SAGRADA");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        glassPanel = new JPanel();
        glassPanel.setLayout(null);
        glassPanel.setOpaque(false);
        mainWindow.setGlassPane(glassPanel);

        showView(new GUIMenuView(this));
        mainWindow.setLocationRelativeTo(null);

        parser = new GUIMessageParser(this);
    }

    @Override
    public String getStartNotificationString()
    {
        return "<html>";
    }

    @Override
    public String getEndNotificationString()
    {
        return "</html>";
    }

    @Override
    public String getColorString(Color color)
    {
        return "<font color='"+ color.toString().toLowerCase() +"'>";
    }

    @Override
    public String getColorEndString()
    {
        return "</font>";
    }

    public void setDimensions(int width, int height)
    {
        mainWindow.getContentPane().setPreferredSize(new Dimension(width, height));
        mainWindow.pack();
    }

    public void refresh()
    {
        glassPanel.validate();
        glassPanel.setVisible(true);
        //mainWindow.pack();
        mainWindow.validate();
        mainWindow.setVisible(true);
        mainWindow.repaint();
    }

    public void showView(GUIView view)
    {
        this.currentView = view;

        view.draw();
        view.drawOnMainWindow();
        refresh();
    }

    public JPanel getGlassPane()
    {
        return glassPanel;
    }

    public void setContainer(Container container)
    {
        mainWindow.setContentPane(container);
    }

    public JFrame getMainWindow()
    {
        return mainWindow;
    }

    public int getScreenRelativeX(JComponent component)
    {
        Point screenPos = new Point();
        SwingUtilities.convertPointToScreen(screenPos, component);

        return (int)screenPos.getX();
    }

    public int getScreenRelativeY(JComponent component)
    {
        Point screenPos = new Point();
        SwingUtilities.convertPointToScreen(screenPos, component);

        return (int)screenPos.getY();
    }

    public int getWindowRelativeX(JComponent component)
    {
        Point screenPos = new Point();
        SwingUtilities.convertPointToScreen(screenPos, component);

        return (int)screenPos.getX() - getMainWindow().getX();
    }

    public int getWindowRelativeY(JComponent component)
    {
        Point screenPos = new Point();
        SwingUtilities.convertPointToScreen(screenPos, component);

        return (int)screenPos.getY() - getMainWindow().getY() - getMainWindow().getInsets().top;
    }

    public void start()
    {
    }

    public void showErrorMessage(String message)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                new GUIOKDialog(thisElement, "Error", message);
            }
        }).start();
    }

    public void showErrorMessage(String title, String message)
    {
        new GUIOKDialog(this, title, message);
    }

    public void showMessage(String message)
    {
        new GUIOKDialog(this, "", message);
    }

    public void showNotification(String message)
    {
        notifications.showNotification(message);
    }

    @Override
    public synchronized void update(Message message)
    {
        setModel(message.getModel());

        message.acceptVisitor(parser);

        new NotificationMessageParser(message, this).showNotification();

        if(getModel().getCurrentPlayer() != null   &&
           getModel().getCurrentPlayer().getNickname().equals(getAssociatedPlayerNickname())   &&
           getModel().getDraftedDie() != null)
        {
            setCursorIcon("resources/images/elements/die/"
                    +getModel().getDraftedDie().getColor().toString().toLowerCase() + "/"
                    +getModel().getDraftedDie().getValue() + ".png");
        }
        else
            setDefaultCursor();

    }

    @Override
    public void showMenu()
    {

    }

    @Override
    public void showLobby()
    {
        showView(new GUILobbyView(this));
    }

    @Override
    public void showTurn()
    {
        reShowCurrentView();
        ((GUIGameView) getCurrentView()).setTurnMode();
    }

    @Override
    public void showDraft()
    {
        reShowCurrentView();
        ((GUIGameView) getCurrentView()).setDraftMode();
    }

    @Override
    public void showAddDie()
    {
        reShowCurrentView();
        ((GUIGameView) getCurrentView()).setAddDieMode();
    }

    @Override
    public void showIncrementDie()
    {

    }

    @Override
    public void showSelectDieFromBoard()
    {
        reShowCurrentView();
        ((GUIGameView) getCurrentView()).setSelectDieFromBoardMode();
    }

    @Override
    public void showSelectDieFromDraftPool()
    {
    }

    @Override
    public void showReDrawDie()
    {
        new GUIDrawDieDialog(this, new Die(Color.RED));
    }

    @Override
    public void showMoveDie()
    {
        reShowCurrentView();
        ((GUIGameView) getCurrentView()).setMoveDieMode();
    }


    @Override
    public void reShowCurrentView()
    {
        if(currentView!=null)
            showView(currentView);
    }

    @Override
    public void disconnect()
    {
    }

    public void setServerIp(String serverIp)
    {
        this.serverIp=serverIp;
    }

    public String getServerIp()
    {
        return serverIp;
    }

    public GUIView getCurrentView()
    {
        return currentView;
    }

    public void setCursorIcon(String iconFileName)
    {
        mainWindow.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(iconFileName).getImage(),
                new Point(0,0),"custom cursor"));
    }

    public void setDefaultCursor()
    {
        mainWindow.setCursor(Cursor.getDefaultCursor());
    }

    public void showOtherPlayersTurn()
    {
        reShowCurrentView();
        ((GUIGameView) getCurrentView()).setOtherPlayersMode();
    }

}
