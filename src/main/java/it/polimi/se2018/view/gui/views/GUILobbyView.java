package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUILobbyView extends GUIView
{
    public GUILobbyView(GUI gui)
    {
        super(gui, 500, 600);
    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(6,1));

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/lobby/back.png"));
        backgroundLabel.setLayout(new GridLayout(1, 0));
        mainContainer.add(backgroundLabel);

        JLabel ipLabel = new JLabel("Server IP: " +gui.getServerIp(), JLabel.CENTER);
        backgroundLabel.add(ipLabel);

        JLabel playerSlots[] = new JLabel[4];

        //first player
        playerSlots[0] = new JLabel();
        try
        {
            gui.getModel().findPlayer(Color.RED);
            playerSlots[0].setIcon(new ImageIcon("resources/images/lobby/red.png"));
        }
        catch (NoElementException e)
        {
            playerSlots[0].setIcon(new ImageIcon("resources/images/lobby/notconnectedred.png"));
        }
        mainContainer.add(playerSlots[0]);

        //second player
        playerSlots[1] = new JLabel();
        try
        {
            gui.getModel().findPlayer(Color.BLUE);
            playerSlots[1].setIcon(new ImageIcon("resources/images/lobby/blue.png"));
        }
        catch (NoElementException e)
        {
            playerSlots[1].setIcon(new ImageIcon("resources/images/lobby/emptyslot.png"));
        }
        mainContainer.add(playerSlots[1]);

        //third player
        playerSlots[2] = new JLabel();
        try
        {
            gui.getModel().findPlayer(Color.GREEN);
            playerSlots[2].setIcon(new ImageIcon("resources/images/lobby/green.png"));
        }
        catch (NoElementException e)
        {
            playerSlots[2].setIcon(new ImageIcon("resources/images/lobby/emptyslot.png"));
        }
        mainContainer.add(playerSlots[2]);

        //fourth player
        playerSlots[3] = new JLabel();
        try
        {
            gui.getModel().findPlayer(Color.YELLOW);
            playerSlots[3].setIcon(new ImageIcon("resources/images/lobby/yellow.png"));
        }
        catch (NoElementException e)
        {
            playerSlots[3].setIcon(new ImageIcon("resources/images/lobby/emptyslot.png"));
        }
        mainContainer.add(playerSlots[3]);

        for(int i=0; i<gui.getModel().getPlayerNum(); i++)
        {
            int index = gui.getModel().getPlayers().get(i).getColor().getNum();
            playerSlots[index].setLayout(new GridLayout(1,1));

            JLabel nicknameLabels = new JLabel(gui.getModel().getPlayers().get(i).getNickname(), JLabel.CENTER);
            nicknameLabels.setFont(new Font("Cooper Std", Font.BOLD, 40));
            nicknameLabels.setForeground(java.awt.Color.orange);
            playerSlots[index].add(nicknameLabels);
        }

        Container container = new Container();
        container.setLayout(new GridLayout(1,2));
        JToggleButton startButton = new JToggleButton();
        startButton.setSelectedIcon(new ImageIcon("resources/images/lobby/readyselected.png"));
        startButton.setIcon(new ImageIcon("resources/images/lobby/ready.png"));
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        container.add(startButton);

        JButton disconnectButton = new JButton();
        disconnectButton.setIcon(new ImageIcon("resources/images/lobby/disconnect.png"));
        disconnectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.getNetworkHandler().disconnect();
            }
        });

        container.add(disconnectButton);

        mainContainer.add(container);

    }
}
