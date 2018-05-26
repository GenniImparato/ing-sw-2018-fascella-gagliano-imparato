package it.polimi.se2018.view.gui;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.views.CLIView;
import it.polimi.se2018.view.gui.dialogs.GUIOKDialog;
import it.polimi.se2018.view.gui.views.GUILobbyView;
import it.polimi.se2018.view.gui.views.GUIMenuView;
import it.polimi.se2018.view.gui.views.GUIView;

import javax.swing.*;
import java.awt.*;

public class GUI extends View
{
    transient private GUIView                   currentView;
    transient private JFrame                    mainWindow;
    transient private String                    serverIp;
    transient private GUIMessageParser          parser;
    transient private GUINotificationSystem     notifications;

    public GUI()
    {
        notifications = new GUINotificationSystem(this);

        mainWindow = new JFrame("SAGRADA");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        showView(new GUIMenuView(this));
        mainWindow.setLocationRelativeTo(null);

        parser = new GUIMessageParser(this);
    }

    public void setDimensions(int width, int height)
    {
        mainWindow.getContentPane().setPreferredSize(new Dimension(width, height));
        mainWindow.pack();
    }

    public void showView(GUIView view)
    {
        this.currentView = view;

        view.draw();
        view.drawOnMainWindow();
        mainWindow.validate();
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public void setContainer(Container container)
    {
        mainWindow.setContentPane(container);
    }

    public JFrame getMainWindow()
    {
        return mainWindow;
    }

    public void start()
    {}

    public void showErrorMessage(String message)
    {
        new GUIOKDialog(this, "Error", message);
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
    public void update(Message message)
    {
        setModel(message.getModel());

        message.acceptVisitor(parser);

    }
    public void refresh()
    {
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

    }

    @Override
    public void showDraft()
    {

    }

    @Override
    public void showAddDie()
    {

    }

    @Override
    public void showIncrementDie()
    {

    }

    @Override
    public void showSelectDieFromBoard()
    {

    }

    @Override
    public void showMoveDie()
    {

    }

    @Override
    public void reShowCurrentView()
    {
        if(currentView!=null)
            showView(currentView);
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

}
