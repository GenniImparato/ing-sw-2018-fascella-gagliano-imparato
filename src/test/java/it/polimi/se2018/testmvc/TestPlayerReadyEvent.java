package it.polimi.se2018.testmvc;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;
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
        view = new TestView();

        view.attach(controller);
        model.attach(view);

        view.notify(new AddPlayerEvent(view, "Karwelox"));      //add the first player
        view.notify(new AddPlayerEvent(view, "Giorgio"));       //add the second player
    }

    /**
     * It tests the case where a player already created is ready to play.
     * Then it tests the case where a player already created isn't ready to play.
     * In the end, it tests the case where a player that doesn't exixt is raedy to play.
     */

    @Test
    public void testPlayerReadyEvent()
    {

        view.notify(new PlayerReadyEvent(view, "Karwelox", true));
        assertEquals(true, controller.getModel().getPlayers().get(0).isReady());


        assertEquals(false, controller.getModel().getPlayers().get(1).isReady());
        view.notify(new PlayerReadyEvent(view, "non presente", true));      //try to set ready a player that doesn't exist

        for(int i=0; i<model.getPlayers().size(); i++)
            assertNotEquals("non presente", model.getPlayers().get(i).getNickname());


    }
}
