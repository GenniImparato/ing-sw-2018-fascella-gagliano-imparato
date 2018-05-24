package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;


public class GUILobbyView extends GUIView
{
    public GUILobbyView(GUI gui)
    {
        super(gui, 500, 750);



    }

    public void draw()
    {
        mainContainer = new Container();

        Container[] containers = new Container[6];      //one container for each rows of the lobby

        mainContainer.setLayout(new GridLayout(6,1));

        containers[0] = new Container();
        containers[0].setLayout(new FlowLayout());

        JLabel ipLabel = new JLabel("Server IP: " +gui.getServerIp());
        containers[0].add(ipLabel);
        mainContainer.add(containers[0]);




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
            playerSlots[1].setIcon(new ImageIcon("resources/images/lobby/notconnectedblue.png"));
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
            playerSlots[2].setIcon(new ImageIcon("resources/images/lobby/notconnectedgreen.png"));
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
            playerSlots[3].setIcon(new ImageIcon("resources/images/lobby/notconnectedyellow.png"));
        }
        mainContainer.add(playerSlots[3]);

        for(int i=0; i<gui.getModel().getPlayerNum(); i++)
        {
            int index = gui.getModel().getPlayers().get(i).getColor().getNum();
            playerSlots[index].setLayout(new GridLayout(1,1));

            JLabel nicknameLabels = new JLabel(gui.getModel().getPlayers().get(i).getNickname(), JLabel.CENTER);
            nicknameLabels.setFont(new Font("Cooper Std", Font.BOLD, 40));
            nicknameLabels.setForeground(java.awt.Color.white);
            playerSlots[index].add(nicknameLabels);
        }


            /*if(i < gui.getModel().getPlayerNum())
            {
                //playerNames[i] = new JLabel("Player" + (i + 1) + ": " + gui.getModel().getPlayers().get(0).getNickname());
                playerNames[i] = new JLabel();
                playerNames[i].setIcon();
            }

            else
                //playerNames[i] = new JLabel("NO PLAYER CONNECTED");

            containers[i+1].add(playerNames[i]);
            mainContainer.add(containers[i+1]);


        containers[5] = new Container();
        containers[5].setLayout(new GridLayout(1,2));

        JButton startGameButton = new JButton();
        startGameButton.setIcon(new ImageIcon("resources/images/menu/connect.png"));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        containers[5].add(startGameButton);
        mainContainer.add(containers[5]);

        */
    }
}
