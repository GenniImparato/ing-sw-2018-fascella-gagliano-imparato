package it.polimi.se2018.testcards.testtoolcards;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.View;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.fail;

public class TestToolCards
{
    private Model model;
    private Controller controller;
    private View view;
    private Board board;
    private SagradaSchemeCardFile sagradaSchemeCardFile;
    private int [] numCards;

    @Before
    public void setUp()
    {
        model = new Model();
        controller = new Controller(model, "./test_resources/toolcards/firstdirectory/");
        view = new TestView();
        view.attach(controller);
        model.attach(view);

        view.notify(new AddPlayerEvent(view, "Player1"));
        view.notify(new PlayerReadyEvent(view, "Player1",true));
        view.notify(new SelectSchemeCardEvent(view,"Player1",1));

        view.notify(new AddPlayerEvent(view, "Player2"));
        view.notify(new PlayerReadyEvent(view, "Player2",true));
        view.notify(new SelectSchemeCardEvent(view,"Player2",1));



        try                                             //try to open a known board
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Sun Catcher.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch (Exception e)
        {
            fail();
        }

        model.getPlayers().get(0).setBoard(board);

        numCards = new int[3];
        
        for(int i=0; i<3; i++)
        {
            if(model.getToolCards().get(i).getName().equals("Copper Foil Burnisher"))
                numCards[0]=i;

            if(model.getToolCards().get(i).getName().equals("Cork Backed Straightedge"))
                numCards[1]=i;

            if(model.getToolCards().get(i).getName().equals("Eglomise Brush"))
                numCards[2]=i;
        }

    }

    @Test
    public void useCopperFoilBurnisher()
    {
        Die die = new Die(Color.RED);
        die.setValue(4);
        try
        {
            board.addDie(die, 0,0,false,false,false);
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }



        view.notify(new UseToolCardEvent(view, numCards[0]));           //use copper foil burnisher

        view.notify(new SelectDieFromBoardEvent(view,0,0));

        view.notify(new MoveSelectedDieEvent(view, 0,2));

        assertEquals(die.getValue(), board.getDie(0,2).getValue());


    }


}
