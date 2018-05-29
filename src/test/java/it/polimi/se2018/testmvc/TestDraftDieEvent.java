package it.polimi.se2018.testmvc;
import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.DraftDieEvent;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestDraftDieEvent
{
    private Model model;
    private Controller controller;
    private View view;

    /**
     * Create the enviroment needed to test the DraftDieEvent. It creates a model, a controller and a view,
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
        view.notify(new AddPlayerEvent(view, "Player2"));

        view.notify(new PlayerReadyEvent(view, "Player1",true));
        view.notify(new PlayerReadyEvent(view, "Player2",true));

        view.notify(new SelectSchemeCardEvent(view,"Player1",1));
        view.notify(new SelectSchemeCardEvent(view,"Player2",1));
    }

    /**
     * Adds a die to the draftpool through the controller; then the added die is removed from the draftpool
     * and confronted to the original added die.
     */
    @Test
    public void testDraftDieEvent()
    {
        Die die = new Die(Color.getRandomColor());
        controller.getModel().getDraftPool().addDie(die);

        Die die2 = controller.getModel().getDraftPool().getAllDice().get(0);
        view.notify(new DraftDieEvent(view, 0));
        Die draftedDie = controller.getModel().getDraftedDie();

        assert die2.isSameDie(draftedDie);
    }
}