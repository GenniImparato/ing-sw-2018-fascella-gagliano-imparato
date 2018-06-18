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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestFluxremGlazhamGrindston
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
        controller = new Controller(model, "./test_resources/toolcards/seconddirectory/");
        view = new TestView();
        view.attach(controller);
        model.attach(view);

        view.notify(new AddPlayerEvent(view, "Player1"));
        view.notify(new AddPlayerEvent(view, "Player2"));

        view.notify(new PlayerReadyEvent(view, "Player1",true));
        view.notify(new PlayerReadyEvent(view, "Player2",true));

        view.notify(new SelectSchemeCardEvent(view,"Player1",1));
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
            if(model.getToolCards().get(i).getName().equals("Grinding Stone"))
                numCards[0]=i;

            if(model.getToolCards().get(i).getName().equals("Glazing Hammer"))
                numCards[1]=i;

            if(model.getToolCards().get(i).getName().equals("Lathekin"))
                numCards[2]=i;
        }

    }

    @Test
    public void useGrindigStone()
    {
        Die die = model.getDraftPool().getAllDice().get(0);
        view.notify(new UseToolCardEvent(view, numCards[0]));//use grinding stone
        int value = die.getValue();
        view.notify(new DraftDieEvent(view,0));

        view.notify(new AddDieToBoardEvent(view,0,0));

        assertEquals(7 - value, board.getDie(0,0).getValue());

    }

    /**
     * Tests if, after the use of the ToolCard "Glazing Hammer", the values of the dice on the DrafPool change.
     * Firstly, it saves the values of the dice, and it matches them with the new values
     * generated with the ReRollDraftPoolAction of the card.
     */
    @Test
    public void useGlazingHammer()
    {
        int[] values = new int[model.getDraftPool().getAllDice().size()];

        for(int i=0; i<model.getDraftPool().getAllDice().size(); i++)               //save the values
            values[i] = model.getDraftPool().getAllDice().get(i).getValue();


        view.notify(new UseToolCardEvent(view, numCards[1])); //use glazing hammer


        for(int i=0; i<model.getDraftPool().getAllDice().size(); i++)
        {
            assertNotEquals(values[i], model.getDraftPool().getAllDice().get(i).getValue());
        }
    }

    /**
     * Tests if, after the use of the ToolCard "Lathekin", we can move two selected dice already added
     * on a board, in some position without ignoring all the restrictions of the selected cell.
     */
    @Test
    public void useLathekin()
    {
        Die die1 = new Die(Color.BLUE);
        die1.setValue(6);
        Die die2 = new Die(Color.RED);
        die2.setValue(3);

        try
        {
            model.getPlayers().get(0).getBoard().addDie(die1, 0,0, false,false,false);
            model.getPlayers().get(0).getBoard().addDie(die2, 1,0, false,false,false);
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }

        assertEquals(6, model.getPlayers().get(0).getBoard().getDie(0,0).getValue());

        view.notify(new UseToolCardEvent(view, numCards[2]));

        view.notify(new SelectDieFromBoardEvent(view,0,0));

        view.notify(new MoveSelectedDieEvent( view, 0,3));      //try to add the selected die in a position ignoring the restriction
        assertEquals(null, model.getPlayers().get(0).getBoard().getDie(0,3));

        view.notify(new MoveSelectedDieEvent(view, 2,0));       //move the first die in a right position
        assertEquals(6, model.getPlayers().get(0).getBoard().getDie(2,0).getValue());

        view.notify(new SelectDieFromBoardEvent(view,1,0));     //move the second die in a right position
        view.notify(new MoveSelectedDieEvent(view, 2,1));

        assertEquals(3, model.getPlayers().get(0).getBoard().getDie(2,1).getValue());





    }

}
