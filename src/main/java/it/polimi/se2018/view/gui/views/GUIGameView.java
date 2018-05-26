package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.*;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.GUIElementBoard;
import it.polimi.se2018.view.gui.elements.GUIElementDraftPool;
import javax.swing.*;
import java.awt.*;


public class GUIGameView extends GUIView
{
    private Board board;
    private DraftPool draftPool;

    public GUIGameView(GUI gui)
    {
        super(gui, 500,500, true);
    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(1,1));


        JPanel mainPanel = new JPanel();

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        mainContainer.add(scrollPane);

        mainPanel.setLayout(new GridLayout(2,1));

        Container firstRowContainer = new Container();
        firstRowContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(firstRowContainer);

        GUIElementDraftPool guiDraftPool = new GUIElementDraftPool(gui.getModel().getDraftPool());
        guiDraftPool.setSelectableDice(true);
        firstRowContainer.add(guiDraftPool);

        Container secondRowContainer = new Container();
        secondRowContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        mainPanel.add(secondRowContainer);

        for(Player player : gui.getModel().getPlayers())
        {
            Container boardContainer = new Container();
            boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.Y_AXIS));      //one box for each player
            secondRowContainer.add(boardContainer);

            GUIElementBoard guiBoard = new GUIElementBoard(player.getBoard());
            guiBoard.setAlignmentX(Component.LEFT_ALIGNMENT);
            guiBoard.setSelectableCells(true);
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

}
