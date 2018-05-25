package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;
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
            playerSlots[0].setIcon(new ImageIcon("resources/images/lobby/emptyslot.png"));
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
            Player player = gui.getModel().getPlayers().get(i);
            int index = player.getColor().getNum();

            playerSlots[index].setLayout(new BoxLayout(playerSlots[index], BoxLayout.X_AXIS));

            playerSlots[index].add(Box.createHorizontalStrut(35));

            JLabel nicknameLabels = new JLabel(player.getNickname(), JLabel.CENTER);
            nicknameLabels.setFont(new Font("Cooper Std", Font.BOLD, 40));
            nicknameLabels.setForeground(java.awt.Color.orange);
            nicknameLabels.setAlignmentY(Component.CENTER_ALIGNMENT);
            playerSlots[index].add(nicknameLabels);

            if(player.isReady())
            {
                playerSlots[index].add(Box.createHorizontalGlue());

                JLabel checkLabel = new JLabel("", JLabel.RIGHT);
                checkLabel.setIcon(new ImageIcon("resources/images/lobby/readycheck.png"));
                playerSlots[index].add(checkLabel);

                playerSlots[index].add(Box.createHorizontalStrut(35));
            }

        }

        Container container = new Container();
        container.setLayout(new GridLayout(1,2));
        JToggleButton readyButton = new JToggleButton();
        readyButton.setSelectedIcon(new ImageIcon("resources/images/lobby/readyselected.png"));
        readyButton.setIcon(new ImageIcon("resources/images/lobby/ready.png"));
        try
        {
            readyButton.setSelected(gui.getModel().findPlayer(gui.getAssociatedPlayerNickname()).isReady());
        }
        catch (NoElementException e)
        {

        }

        readyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.notify(new PlayerReadyEvent(gui, gui.getAssociatedPlayerNickname(), readyButton.isSelected()));
            }
        });

        container.add(readyButton);

        JButton disconnectButton = new JButton();
        disconnectButton.setIcon(new ImageIcon("resources/images/lobby/disconnect.png"));
        disconnectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.getNetworkHandler().disconnect();
                gui.showView(new GUIMenuView(gui));
            }
        });

        container.add(disconnectButton);

        mainContainer.add(container);

    }
}
