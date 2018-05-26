package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;

import it.polimi.se2018.view.gui.elements.GUIElementBoard;
import it.polimi.se2018.view.gui.elements.GUIElementDie;
import it.polimi.se2018.view.gui.elements.GUIElementDraftPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIGameView extends GUIView
{
    private Board board;
    private DraftPool draftPool;

    public GUIGameView(GUI gui)
    {
        super(gui, 1200,700);
    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout());


        //try to add a board with some dice
        try
        {
            SagradaSchemeCardFile sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Aurora Sagradis.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e)
        {

        }
        try
        {
            board.addDie(new Die(Color.RED), 0,0);
            board.addDie(new Die(Color.PURPLE), 1,1);
            board.addDie(new Die(Color.BLUE), 0,2);
            board.addDie(new Die(Color.GREEN), 1,3);
            board.addDie(new Die(Color.YELLOW), 0,4);
        }
        catch(ChangeModelStateException e)
        {

        }

        GUIElementBoard guiBoard = new GUIElementBoard(board);
        guiBoard.setSelectableDice(true);


        mainContainer.add(guiBoard);

        //try to add the draftpool

        draftPool = new DraftPool(new DiceBag());
        draftPool.draw(9);

        GUIElementDraftPool guiDraftPool = new GUIElementDraftPool(draftPool);
        guiDraftPool.setSelectableDice(true);
        mainContainer.add(guiDraftPool);

        JButton button = new JButton("(|)");
        mainContainer.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainContainer.remove(guiDraftPool);
                GUIElementDraftPool guiDraftPool = new GUIElementDraftPool(draftPool);
                guiDraftPool.setSelectableDice(true);
                mainContainer.add(guiDraftPool);

                gui.reShowCurrentView();


            }
        });


    }

}
