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

/**
 * Class used to test three Tool Cards: "Copper Foil Burnisher, Cork Backed Straightedge, Eglomise Brush"
 */
public class TestCopperCorkEglomise
{
    private Model model;
    private Controller controller;
    private View view;
    private Board board;
    private SagradaSchemeCardFile sagradaSchemeCardFile;
    private int [] numCards;

    /**
     * Method that sets a game table with the three Tool Cards selected. ù
     * It uses the Controller with the parameter of the path directory where there are the selected cards,
     * and sets the index of the card in the array "numCards"
     */
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

    /**
     * Tests the use of the CopperFoilBurnisher, it tries to move a Die in a position ignoring the value restriction.
     * Then it tries to add the same die in a position where there is a color restriction
     */
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

        view.notify(new SelectDieFromBoardEvent(view,0,0));     //select a Die just added on the board
        view.notify(new MoveSelectedDieEvent(view, 0,2));       //and we add it in a position ignoring the value restriction

        assertEquals(die.getValue(), board.getDie(0,2).getValue());


        view.notify(new SelectDieFromBoardEvent(view,0,2));     //select the Die
        view.notify(new MoveSelectedDieEvent(view, 0,1));       //and we try to add it in a position with a different color restriction

        assertEquals(null, board.getDie(0,1));

    }

    @Test
    public void useCorkBackedStraightedge()
    {
        Die die = new Die(Color.RED);
        die.setValue(4);
        try
        {
            board.addDie(die, 2,0,false,false,false);
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }

        view.notify(new UseToolCardEvent(view, numCards[1]));           //use cork backed straightedge
        int value = model.getDraftPool().getAllDice().get(0).getValue();
        view.notify(new DraftDieEvent(view, 0));


        view.notify(new AddDieToBoardEvent(view, 2,4));     //add a die withouth respecting the adjacent restrictions

        //assertNotEquals(null, board.getDie(2,1));
        assertEquals(value, board.getDie(2,4).getValue());


    }



}
