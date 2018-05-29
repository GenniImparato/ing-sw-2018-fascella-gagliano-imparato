package it.polimi.se2018.testmvc;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.gui.GUI;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPlayerReadyEvent
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
    public void testPlayerReadyEvent()
    {
        view.notify(new AddPlayerEvent(view, "Karwelox"));                      //add the first player that is ready
        view.notify(new PlayerReadyEvent(view, "Karwelox", true));
        assertEquals(true, controller.getModel().getPlayers().get(0).isReady());

        view.notify(new AddPlayerEvent(view, "Giorgio"));
        assertEquals(false, controller.getModel().getPlayers().get(1).isReady());       //the second player just created is not ready

        view.notify(new PlayerReadyEvent(view, "non presente", true));      //try to set ready a player that doesn't exist
        for(int i=0; i<model.getPlayers().size(); i++)
            assertNotEquals("non presente", model.getPlayers().get(i).getNickname());



    }
}
