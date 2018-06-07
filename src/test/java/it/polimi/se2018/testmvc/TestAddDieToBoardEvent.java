package it.polimi.se2018.testmvc;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.view.View;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestAddDieToBoardEvent
{
    private Model model;
    private Controller controller;
    private View view;
    private Board board;
    private SagradaSchemeCardFile sagradaSchemeCardFile;
    private Die draftedDie;

    /**
     * Create the environment needed to test the DraftDieEvent. It creates a model, a controller and a view,
     * respecting the MVC logic.
     */
    @Before
    public void setUp()
    {
        model = new Model();
        controller = new Controller(model);
        view = new TestView();
        view.attach(controller);
        model.attach(view);

        view.notify(new AddPlayerEvent(view, "Player1"));

        view.notify(new PlayerReadyEvent(view, "Player1",true));

        view.notify(new SelectSchemeCardEvent(view,"Player1",1));

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
        assertEquals("Sun Catcher", model.getPlayers().get(0).getBoard().getSchemeCardName());
        assertEquals(3,controller.getModel().getDraftPool().getAllDice().size());

        view.notify(new DraftDieEvent(view,0)); //drafted die from the draftpool
        draftedDie = model.getDraftedDie();         //saves the reference to a drafted die
    }

    /**
     * Tests if a drafted Die is added to a selected board of a player
     */
    @Test
    public void testAddDraftedDieEvent()
    {
        view.notify(new AddDieToBoardEvent(view,0,0));

        assertEquals(draftedDie.getValue(), model.getPlayers().get(0).getBoard().getDie(0,0).getValue());
        assertEquals(draftedDie.getColor(), model.getPlayers().get(0).getBoard().getDie(0,0).getColor());
    }
}
