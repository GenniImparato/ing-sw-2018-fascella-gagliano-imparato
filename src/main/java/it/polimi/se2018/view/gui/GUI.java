package it.polimi.se2018.view.gui;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.gui.views.GUIConnectionView;
import it.polimi.se2018.view.gui.views.GUIMenuView;
import it.polimi.se2018.view.gui.views.GUIView;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        mainWindow.setLocationRelativeTo(null);

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

    public void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(mainWindow, message);
    }

    public void showErrorMessage(String title, String message)
    {
        JDialog dialog = new JDialog(mainWindow, title, true);
        Container mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog.setContentPane(mainContainer);
        dialog.setResizable(false);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setPreferredSize(new Dimension(400, 300));
        //backgroundLabel.setIcon(new ImageIcon("resources/images/menu/back1.png"));
        mainContainer.add(backgroundLabel);

        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));
        backgroundLabel.add(Box.createVerticalStrut(30));

        JLabel errorLabel = new JLabel(message);
        backgroundLabel.add(errorLabel);
        backgroundLabel.add(Box.createVerticalStrut(100));

        JButton closeButton = new JButton();
        closeButton.setPreferredSize(new Dimension(150, 80));
        closeButton.setIcon(new ImageIcon("resources/images/menu/connecttoserver.png"));
        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.setVisible(false);
            }
        });

        backgroundLabel.add(closeButton);

        dialog.pack();
        dialog.validate();
        dialog.setLocationRelativeTo(mainWindow);
        dialog.setVisible(true);

    }

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
