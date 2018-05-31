package it.polimi.se2018.view.gui;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.views.CLIView;
import it.polimi.se2018.view.gui.dialogs.GUIOKDialog;
import it.polimi.se2018.view.gui.views.GUIGameView;
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
    transient private JPanel                    glassPanel;

    public GUI()
    {
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

    public void setDimensions(int width, int height)
    {
        mainWindow.getContentPane().setPreferredSize(new Dimension(width, height));
        mainWindow.pack();
    }

    public void refresh()
    {
        glassPanel.validate();
        glassPanel.setVisible(true);
        mainWindow.pack();
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

    public int getWindowRealtiveX(JComponent component)
    {
        Point screenPos = new Point();
        SwingUtilities.convertPointToScreen(screenPos, component);

        return (int)screenPos.getX() - getMainWindow().getX();
    }

    public int getWindowRealtiveY(JComponent component)
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

    }

    @Override
    public void showSelectDieFromDraftPool() {

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
