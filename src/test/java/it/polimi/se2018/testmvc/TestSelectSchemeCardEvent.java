package it.polimi.se2018.testmvc;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.gui.GUI;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSelectSchemeCardEvent
{
    private Model model;
    private Controller controller;
    private View view;

    /**
     * In this method the server-client connection is by-passed in order to test the MVC communication.
     * The view is observable from the controller and the model is observable from the view
     * The controller will modify its model.
     */
    @Before
    public void setUp()
    {
        model = new Model();
        controller = new Controller(model);
        view = new TestView();

        view.attach(controller);
        model.attach(view);

        view.notify(new AddPlayerEvent(view, "Karwelox"));
    }


    /**
     *
     */
    @Test
    public void testSelectSchemeCardEvent()
    {

        SelectSchemeCardEvent event = new SelectSchemeCardEvent(view, "Karwelox", 3);
        view.notify(event);

        assertEquals(model.getSchemeCards().get(model.getPlayers().get(0).getSchemeCardIndex(3)), model.getPlayers().get(0).getBoard());
        assertEquals(true, model.getPlayers().get(0).hasChoosenSchemeCard());
    }

    /**
     * It tries to select a schemecard out of the range of choice
     * In the first case, the choice is smaller than the minimum value of the range
     * In the second case, the choice is bigger than the maximum value of the range
     */
    @Test
    public void testSelectWrongSchemeCard()
    {
        SelectSchemeCardEvent event1 = new SelectSchemeCardEvent(view, "Karwelox", -1);
        view.notify(event1);
        assertEquals(false, model.getPlayers().get(0).hasChoosenSchemeCard());


        SelectSchemeCardEvent event2 = new SelectSchemeCardEvent(view, "Karwelox", 7);
        view.notify(event2);
        assertEquals(false, model.getPlayers().get(0).hasChoosenSchemeCard());
    }

}
