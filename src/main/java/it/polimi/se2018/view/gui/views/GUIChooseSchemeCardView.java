package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Cell;
import it.polimi.se2018.mvc_comunication.events.SelectDieFromBoardEvent;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.GUIBoardActions;
import it.polimi.se2018.view.gui.elements.GUIElementBoard;
import it.polimi.se2018.view.gui.elements.GUIElementCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIChooseSchemeCardView extends GUIView
{
    private Board[] schemeCard;
    private GUIElementBoard guiBoard[];
    private int chosenCard;
    private JLabel mainTextLabel;

    public GUIChooseSchemeCardView(GUI gui, Board[] schemeCards)
    {
        super(gui, 850,740, false);

        this.schemeCard=schemeCards;
        guiBoard = new GUIElementBoard[4];
    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout());

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/selectschemes/back.png"));
        mainContainer.add(backgroundLabel);

        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));

        backgroundLabel.add(Box.createVerticalStrut(28));

        mainTextLabel = new JLabel("Choose a scheme card to play with.", JLabel.CENTER);
        mainTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundLabel.add(mainTextLabel);

        backgroundLabel.add(Box.createVerticalStrut(12));

        Container boxContainer = new Container();
        boxContainer.setLayout(new GridLayout(2,2));
        backgroundLabel.add(boxContainer);

        for(int i=0; i<4; i++)
        {
            Container boardContainer = new Container();
            boardContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            boxContainer.add(boardContainer);

            guiBoard[i] = new GUIElementBoard(schemeCard[i], gui);
            guiBoard[i].setSelectable(true);

            guiBoard[i].setActions(new GUIBoardActions()
            {
                @Override
                public void clicked(GUIElementBoard board)
                {
                    int choice;
                    for(choice=0; choice<4; choice++)
                        if(board == guiBoard[choice])
                            break;

                    gui.notify(new SelectSchemeCardEvent(gui, gui.getAssociatedPlayerNickname(), choice));
                }

                @Override
                public void clickedCell(GUIElementBoard board, int row, int column)
                {
                }
            });


            boardContainer.add(guiBoard[i]);

            JLabel blankAreaLabel = new JLabel();
            blankAreaLabel.setIcon(new ImageIcon("resources/images/selectschemes/blankarea.png"));
            boardContainer.add(blankAreaLabel);

            blankAreaLabel.setLayout(new GridLayout(2, 1));

            blankAreaLabel.add(new JLabel(schemeCard[i].getSchemeCardName(), JLabel.CENTER));

            Container difficultyGrid = new Container();
            difficultyGrid.setLayout(new GridLayout(1, 2));
            blankAreaLabel.add(difficultyGrid);

            difficultyGrid.add(new JLabel("Difficulty:", JLabel.CENTER));

            Container difficultyFlow = new Container();
            difficultyFlow.setLayout(new FlowLayout());
            difficultyGrid.add(difficultyFlow);

            for(int j=0; j<schemeCard[i].getDifficulty(); j++)
            {
                JLabel tokenLabel = new JLabel("", JLabel.CENTER);
                tokenLabel.setIcon(new ImageIcon("resources/images/selectschemes/difficulty.png"));
                difficultyFlow.add(tokenLabel);
            }
        }
    }

    public void setCardChosen(boolean cardChosen)
    {
        for(int i=0; i<4; i++)
            guiBoard[i].setSelectable(!cardChosen);        //if the card is chosen, you can't select the other cards

        if(cardChosen)
        {
            guiBoard[chosenCard].showSelected();
            mainTextLabel.setText("Waiting for other players to choose...");
        }

    }
}

