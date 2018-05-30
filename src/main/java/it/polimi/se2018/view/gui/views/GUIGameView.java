package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.*;
import it.polimi.se2018.mvc_comunication.events.AddDraftedDieEvent;
import it.polimi.se2018.mvc_comunication.events.DraftDieEvent;
import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GUIGameView extends GUIView
{
    private GUIElementDraftPool     guiDraftPool;
    private GUIElementRoundTrack    guiRoundTrack;
    private List<GUIElementBoard>   guiBoards;
    private List<JLabel>            boardContainers;
    private GUIElementToolCard[]    guiToolCards;
    private GUIElementPublicCard[]  guiPublicCards;

    private JButton                 endTurnButton;
    private JLabel                  actionLabel;

    private GUIElementDie           draftedDie;

    public GUIGameView(GUI gui)
    {
        super(gui, 500,500, true);

        guiBoards = new ArrayList<>();
        boardContainers = new ArrayList<>();
        guiToolCards = new GUIElementToolCard[3];
        guiPublicCards = new GUIElementPublicCard[3];


        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(1,1));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        mainContainer.add(scrollPane);

        Container leadingContainer = new Container();       //contains the label and the button
        leadingContainer.setLayout(new BorderLayout());
        leadingContainer.setMaximumSize(new Dimension(1000, 50));
        mainPanel.add(leadingContainer);

        actionLabel = new JLabel("", JLabel.LEFT);
        actionLabel.setFont(new Font("SansSerif", Font.PLAIN, 23));
        actionLabel.setOpaque(false);
        leadingContainer.add(actionLabel, BorderLayout.WEST);

        Container endTurnButtonContainer = new Container();
        endTurnButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        leadingContainer.add(endTurnButtonContainer);

        endTurnButton = new JButton();
        endTurnButton.setIcon(new ImageIcon("resources/images/gameview/endturn.png"));
        endTurnButton.setPreferredSize(new Dimension(250, 50));
        endTurnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.notify(new EndTurnEvent(gui));
            }
        });

        endTurnButtonContainer.add(endTurnButton, BorderLayout.CENTER);

        Container gridContainer = new Container();              //contains the draftpool and the boards
        gridContainer.setLayout(new GridLayout(2,1));
        mainPanel.add(gridContainer);

        Container firstRowContainer = new Container();
        firstRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        gridContainer.add(firstRowContainer);

        guiDraftPool = new GUIElementDraftPool(gui.getModel().getDraftPool(), gui);
        firstRowContainer.add(guiDraftPool);

        Container roundCardsBox = new Container();
        roundCardsBox.setLayout(new BoxLayout(roundCardsBox, BoxLayout.Y_AXIS));
        firstRowContainer.add(roundCardsBox);


        guiRoundTrack = new GUIElementRoundTrack(gui.getModel().getRoundTrack(), guiDraftPool, gui);
        roundCardsBox.add(guiRoundTrack);

        roundCardsBox.add(Box.createVerticalStrut(50));

        Container cardsContainer = new Container();
        cardsContainer.setLayout(new FlowLayout());
        roundCardsBox.add(cardsContainer);

        for(int i=0; i<3; i++)
        {
            guiToolCards[i] = new GUIElementToolCard(gui.getModel().getToolCards().get(i), i, gui);
            cardsContainer.add(guiToolCards[i]);
            guiToolCards[i].playGeneratedAnimation(200 + i*300);
        }

        for(int i=0; i<3; i++)
        {
            guiPublicCards[i] = new GUIElementPublicCard(gui.getModel().getPublicObjectiveCards().get(i), gui);
            cardsContainer.add(guiPublicCards[i]);
            guiPublicCards[i].playGeneratedAnimation(1100 + i*300);
        }

        Container secondRowContainer = new Container();
        secondRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        gridContainer.add(secondRowContainer);

        for(Player player : gui.getModel().getPlayers())
        {
            JLabel boardContainer = new JLabel();
            boardContainers.add(boardContainer);
            boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.Y_AXIS));      //one box for each player
            boardContainer.setIcon(new ImageIcon("resources/images/gameview/blankcontainer.png"));
            secondRowContainer.add(boardContainer);

            boardContainer.add(Box.createVerticalStrut(70));

            GUIElementBoard guiBoard = new GUIElementBoard(player.getBoard(), gui);
            guiBoards.add(guiBoard);
            guiBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
            boardContainer.add(guiBoard);

            JLabel nicknameLabel = new JLabel(player.getNickname(), JLabel.CENTER);
            nicknameLabel.setFont(new Font("Cooper Std", Font.BOLD, 35));
            nicknameLabel.setForeground(java.awt.Color.red);
            nicknameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            boardContainer.add(nicknameLabel);

            Container tokensGrid = new Container();
            tokensGrid.setLayout(new GridLayout(1, 2));
            boardContainer.add(tokensGrid);

            Container tokensFlow = new Container();
            tokensFlow.setLayout(new FlowLayout());
            boardContainer.add(tokensFlow);

            tokensFlow.add(new JLabel("Favor Tokens:", JLabel.CENTER));

            for(int j=0; j< player.getTokens(); j++)
            {
                JLabel tokenLabel = new JLabel("", JLabel.CENTER);
                tokenLabel.setIcon(new ImageIcon("resources/images/selectschemes/difficulty.png"));
                tokensFlow.add(tokenLabel);
            }

            boardContainer.add(Box.createVerticalGlue());
        }
    }

    public void draw()
    {
        Player currentplayer = gui.getModel().getCurrentPlayer();
        if(currentplayer!=null && currentplayer.getNickname().equals(gui.getAssociatedPlayerNickname()))       //you can push the end button only if it's your turn
            endTurnButton.setEnabled(true);
        else
            endTurnButton.setEnabled(false);


        guiRoundTrack.refresh(gui.getModel().getRoundTrack());
        guiDraftPool.refresh(gui.getModel().getDraftPool());

        for(int i=0; i<gui.getModel().getPlayerNum(); i++)
        {
            guiBoards.get(i).refresh(gui.getModel().getPlayers().get(i).getBoard(), draftedDie);
        }

        refreshBoardContainers();
    }

    public void setTurnMode()
    {
        actionLabel.setText("Choose a die from the draft pool or use a tool card!");

        setCardsSelectable(true);

        for(GUIElementBoard board : guiBoards)
        {
            board.setSelectable(false);
            board.setSelectableCells(false);
            board.setSelectableDice(false);
        }

        guiDraftPool.setSelectableDice(true);
        guiDraftPool.setActions(new GUIDraftPoolActions()
        {
            @Override
            public void dieClicked(GUIElementDraftPool draftPool, GUIElementDie die, int dieNum)
            {
                gui.notify(new DraftDieEvent(gui, dieNum));
            }
        });
    }

    public void setAddDieMode()
    {
        actionLabel.setText("Choose where to add the drafted die in your board!");

        setCardsSelectable(false);
        guiDraftPool.setSelectableDice(false);
        endTurnButton.setEnabled(false);

        for(int i=0; i<gui.getModel().getPlayerNum(); i++)
        {
            if (gui.getModel().getPlayers().get(i).getNickname().equals(gui.getAssociatedPlayerNickname()))
            {
                guiBoards.get(i).setSelectableCells(true);
                guiBoards.get(i).setActions(new GUIBoardActions()
                {
                    @Override
                    public void clicked(GUIElementBoard board)
                    {
                    }

                    @Override
                    public void clickedCell(GUIElementBoard board, int row, int column)
                    {
                        gui.notify(new AddDraftedDieEvent(gui, row, column));
                    }
                });
            }
            else
                guiBoards.get(i).setSelectableCells(false);
        }
    }

    public void setOtherPlayersMode()
    {
        actionLabel.setText("");

        setCardsSelectable(false);
        for(GUIElementBoard guiBoard : guiBoards)
        {
            guiBoard.setSelectable(false);
            guiBoard.setSelectableCells(false);
            guiBoard.setSelectableDice(false);
        }

        guiDraftPool.setSelectableDice(false);
    }

    public void moveDraftedDieToBoardAnimation(Die die, Player player)
    {
        for(int i=0; i<gui.getModel().getPlayers().size(); i++)
        {
            if(player.getNickname().equals(gui.getModel().getPlayers().get(i).getNickname()))
            {
                draftedDie = guiDraftPool.getGUIDie(die);
                if(draftedDie != null)
                    draftedDie.playMoveToBoardAnimation(guiBoards.get(i));
            }
        }

    }

    private void setCardsSelectable(boolean selectable)
    {
        for(int i=0; i<3; i++)
            guiToolCards[i].setSelectable(selectable);
    }

    private void refreshBoardContainers()
    {
        Player currentPlayer = gui.getModel().getCurrentPlayer();

        if(currentPlayer == null)
            return;

        for(int i=0; i<gui.getModel().getPlayerNum(); i++)
        {
            Player player = gui.getModel().getPlayers().get(i);

            if (player.getNickname().equals(gui.getAssociatedPlayerNickname())  && !currentPlayer.getNickname().equals(gui.getAssociatedPlayerNickname()))
                boardContainers.get(i).setIcon(new ImageIcon("resources/images/gameview/yourboard.png"));
            else if (player.getNickname().equals(gui.getAssociatedPlayerNickname()))
                boardContainers.get(i).setIcon(new ImageIcon("resources/images/gameview/yourboardturn.png"));
            else if (currentPlayer.getNickname().equals(player.getNickname()))
                boardContainers.get(i).setIcon(new ImageIcon("resources/images/gameview/turn.png"));
            else
                boardContainers.get(i).setIcon(new ImageIcon("resources/images/gameview/blankcontainer.png"));
        }
    }

}
