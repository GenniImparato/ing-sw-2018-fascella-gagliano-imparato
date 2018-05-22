package it.polimi.se2018.view.gui;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.gui.views.GUIConnectionView;
import it.polimi.se2018.view.gui.views.GUIMenuView;
import it.polimi.se2018.view.gui.views.GUIView;

import javax.swing.*;
import java.awt.*;

public class GUI extends View
{
    private GUIView currentView;
    private JFrame mainWindow;

    public GUI()
    {
        mainWindow = new JFrame("SAGRADA");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        showView(new GUIMenuView(this));

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
        mainWindow.validate();
        mainWindow.setVisible(true);
    }

    public void setContainer(Container container)
    {
        mainWindow.setContentPane(container);
    }

    public void start(){}
    public void showErrorMessage(String message){}
    public void showMessage(String message){}
    @Override
    public void update(Message event)
    {
    }
    public void refresh()
    {
    }

    @Override
    public void showMenu()
    {

    }

    @Override
    public void showLobby() {

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

    }

}
