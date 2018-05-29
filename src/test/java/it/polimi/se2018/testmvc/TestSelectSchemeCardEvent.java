package it.polimi.se2018.testmvc;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import it.polimi.se2018.view.View;
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
        view = new GUI();

        view.attach(controller);
        model.attach(view);
    }


    @Test
    public void testSelectSchemeCardEvent()
    {
        view.notify(new AddPlayerEvent(view, "Karwelox"));

        SelectSchemeCardEvent event = new SelectSchemeCardEvent(view, "Karwelox", 3);
        view.notify(event);

        assertEquals(model.getChosenBoard(0), model.getPlayers().get(0).getBoard());

    }
}
