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

    public GUIChooseSchemeCardView(GUI gui, Board[] schemeCards)
    {
        super(gui, 800,600);

        this.schemeCard=schemeCards;
        guiBoard = new GUIElementBoard[4];

    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout());


        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/menu/sagrada.png"));
        backgroundLabel.setPreferredSize(new Dimension(800, 600));

        backgroundLabel.setLayout(new GridLayout(2,2));
        mainContainer.add(backgroundLabel);

        for(int i=0; i<4; i++)
        {
            Container boardContainer = new Container();
            boardContainer.setLayout(new FlowLayout((FlowLayout.CENTER)));

            guiBoard[i] = new GUIElementBoard(schemeCard[i]);

            boardContainer.add(guiBoard[i]);
            backgroundLabel.add(boardContainer);
        }

        guiBoard[0].setActions(new GUIBoardActions()
        {
            @Override
            public void clicked()
            {
                chosenCard = 0;
                gui.notify(new SelectSchemeCardEvent(gui, gui.getAssociatedPlayerNickname(), 0));
            }
        });

        guiBoard[1].setActions(new GUIBoardActions()
        {
            @Override
            public void clicked()
            {
                chosenCard = 1;
                gui.notify(new SelectSchemeCardEvent(gui, gui.getAssociatedPlayerNickname(), 1));
            }
        });

        guiBoard[2].setActions(new GUIBoardActions()
        {
            @Override
            public void clicked()
            {
                chosenCard = 2;
                gui.notify(new SelectSchemeCardEvent(gui, gui.getAssociatedPlayerNickname(), 2));
            }
        });

        guiBoard[3].setActions(new GUIBoardActions()
        {
            @Override
            public void clicked()
            {
                chosenCard = 3;
                gui.notify(new SelectSchemeCardEvent(gui, gui.getAssociatedPlayerNickname(), 3));
            }
        });

    }

    public void setCardChosen(boolean cardChosen)
    {
        for(int i=0; i<4; i++)
            guiBoard[i].setSelectable(!cardChosen);        //if the card is chosen, you can't select the other cards

        guiBoard[chosenCard].showSelected();

    }
}

