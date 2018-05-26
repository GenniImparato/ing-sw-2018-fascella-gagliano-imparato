package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;

import it.polimi.se2018.view.gui.elements.GUIElementBoard;
import it.polimi.se2018.view.gui.elements.GUIElementDie;

import java.awt.*;


public class GUIGameView extends GUIView
{
    private Board board;

    public GUIGameView(GUI gui)
    {
        super(gui, 1200,700);
    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout());


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

    }

}
