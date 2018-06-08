package it.polimi.se2018.testmvc;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class used to test the EndTurnEvent class.
 * @author Matteo Gagliano, Carmelo Fascella
 */

public class TestEndTurnEvent
{
    private Controller controller;
    private Model model;
    private TestView view;

    /**
     * Create the environment needed to test the EndTurnEvent. It creates a model, a controller and a view,
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

        view.notify(new AddPlayerEvent(view,"imat"));
        view.notify(new AddPlayerEvent(view,"kwx"));

        view.notify(new PlayerReadyEvent(view,"imat", true));
        view.notify(new PlayerReadyEvent(view,"kwx", true));

        view.notify(new SelectSchemeCardEvent(view,"imat",0));
        view.notify(new SelectSchemeCardEvent(view,"kwx",0));

    }

    /**
     * This method checks that at the beginning the current round is zero;
     * after the first player ends the turn, it is still zero;
     * after the two players end two times their turn, the current round gets 1
     */
    @Test
    public void testEndTurnEvent()
    {
        assertEquals(0,model.getCurrentRound());

        view.notify(new EndTurnEvent(view));
        assertEquals(0,model.getCurrentRound());

        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));

        assertEquals(1,model.getCurrentRound());
    }

}