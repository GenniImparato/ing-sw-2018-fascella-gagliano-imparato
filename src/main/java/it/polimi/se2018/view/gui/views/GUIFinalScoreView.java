package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIFinalScoreView extends GUIView
{
    public GUIFinalScoreView(GUI gui)
    {
        super(gui, 500,500, false);
    }

    @Override
    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(6,1));

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/lobby/back.png"));
        backgroundLabel.setLayout(new GridLayout(1, 0));
        mainContainer.add(backgroundLabel);


        JLabel playerSlots[] = new JLabel[4];

        //first player
        playerSlots[0] = new JLabel();
        try
        {
            gui.getModel().findPlayer(it.polimi.se2018.utils.Color.RED);
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
            gui.getModel().findPlayer(it.polimi.se2018.utils.Color.BLUE);
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
            gui.getModel().findPlayer(it.polimi.se2018.utils.Color.GREEN);
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

            playerSlots[index].add(Box.createHorizontalGlue());

            JLabel scoreLabel = new JLabel(Integer.toString(player.getScore()), JLabel.RIGHT);
            scoreLabel.setFont(new Font("Cooper Std", Font.BOLD, 40));
            scoreLabel.setForeground(java.awt.Color.orange);
            scoreLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            playerSlots[index].add(scoreLabel);


            playerSlots[index].add(Box.createHorizontalStrut(35));
            }


        JButton exitButton = new JButton();
        exitButton.setPreferredSize(new Dimension(355, 200));
        exitButton.setIcon(new ImageIcon(""));
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                gui.showView(new GUIMenuView(gui));
            }

        });

        mainContainer.add(exitButton);

    }






}

