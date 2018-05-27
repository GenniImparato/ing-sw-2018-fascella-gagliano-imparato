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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class GUIGameView extends GUIView
{
    private GUIElementDraftPool     guiDraftPool;
    private List<GUIElementBoard>   guiBoards;
    private JButton                 endTurnButton;

    public GUIGameView(GUI gui)
    {
        super(gui, 500,500, true);

        guiBoards = new ArrayList<>();

        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(1,1));


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        mainContainer.add(scrollPane);

        Container leadingContainer = new Container();       //contains the label and the button
        leadingContainer.setLayout(new FlowLayout());
        mainPanel.add(leadingContainer);

        JLabel actionLabel = new JLabel("Vediamolo");
        endTurnButton = new JButton("END TURN");
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.notify(new EndTurnEvent(gui));
            }
        });

        leadingContainer.add(actionLabel);
        leadingContainer.add(endTurnButton);

        Container gridContainer = new Container();              //contains the draftpool and the boards
        gridContainer.setLayout(new GridLayout(2,1));
        mainPanel.add(gridContainer);

        Container firstRowContainer = new Container();
        firstRowContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        gridContainer.add(firstRowContainer);

        guiDraftPool = new GUIElementDraftPool(gui.getModel().getDraftPool());
        firstRowContainer.add(guiDraftPool);

        Container secondRowContainer = new Container();
        secondRowContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        gridContainer.add(secondRowContainer);

        for(Player player : gui.getModel().getPlayers())
        {
            Container boardContainer = new Container();
            boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.Y_AXIS));      //one box for each player
            secondRowContainer.add(boardContainer);

            GUIElementBoard guiBoard = new GUIElementBoard(player.getBoard());
            guiBoards.add(guiBoard);
            guiBoard.setAlignmentX(Component.LEFT_ALIGNMENT);
            boardContainer.add(guiBoard);

            JLabel blankAreaLabel = new JLabel("", JLabel.LEFT);
            blankAreaLabel.setIcon(new ImageIcon("resources/images/selectschemes/blankarea.png"));
            blankAreaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            boardContainer.add(blankAreaLabel);

            blankAreaLabel.setLayout(new GridLayout(2, 1));

            blankAreaLabel.add(new JLabel(player.getNickname(), JLabel.CENTER));

            Container tokensGrid = new Container();
            tokensGrid.setLayout(new GridLayout(1, 2));
            blankAreaLabel.add(tokensGrid);

            tokensGrid.add(new JLabel("Favor Tokens:", JLabel.CENTER));

            Container tokensFlow = new Container();
            tokensFlow.setLayout(new FlowLayout());
            tokensGrid.add(tokensFlow);

            for(int j=0; j< player.getTokens(); j++)
            {
                JLabel tokenLabel = new JLabel("", JLabel.CENTER);
                tokenLabel.setIcon(new ImageIcon("resources/images/selectschemes/difficulty.png"));
                tokensFlow.add(tokenLabel);
            }
        }

    }

    public void draw()
    {
        Player currentplayer = gui.getModel().getCurrentPlayer();
        if(currentplayer!=null && currentplayer.getNickname().equals(gui.getAssociatedPlayerNickname()))       //you can push the end button only if it's your turn
            endTurnButton.setEnabled(true);
        else
            endTurnButton.setEnabled(false);


        guiDraftPool.refresh(gui.getModel().getDraftPool());

        for(int i=0; i<gui.getModel().getPlayerNum(); i++)
        {
            guiBoards.get(i).refresh(gui.getModel().getPlayers().get(i).getBoard());
        }
    }

    public void setTurnMode()
    {


        guiDraftPool.setSelectableDice(true);
        guiDraftPool.setActions(new GUIDraftPoolActions()
        {
            @Override
            public void dieClicked(GUIElementDraftPool draftPool, GUIElementDie die, int dieNum)
            {
                gui.notify(new DraftDieEvent(gui, dieNum));
            }
        });

        for(GUIElementBoard board : guiBoards)
            board.setSelectableCells(false);
    }

    public void setAddDieMode()
    {


        guiDraftPool.setSelectableDice(false);

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


        for(GUIElementBoard guiBoard : guiBoards)
        {
            guiBoard.setSelectable(false);
            guiBoard.setSelectableCells(false);
        }

        guiDraftPool.setSelectableDice(false);
    }

}
