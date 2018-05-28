package it.polimi.se2018;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.gui.GUI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMVC
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
        view = new CLI(true);

        view.attach(controller);
        model.attach(view);
    }

    /**
     * Tests that at the beginning the model has no player
     * Tests the adding of a new player by the view that notifies the controller.
     *
     */
    @Test
    public void testAddPlayerEvent()
    {
        assertEquals(0,controller.getModel().getPlayerNum());

        view.notify(new AddPlayerEvent(view, "Karwelox"));      //add the first player
        assertEquals(1, controller.getModel().getPlayerNum());
        assertEquals("Karwelox", controller.getModel().getPlayers().get(0).getNickname());

        view.notify(new AddPlayerEvent(view, "Generoso the Paffut"));
        assertEquals(2, controller.getModel().getPlayerNum());
        assertEquals("Generoso the Paffut", controller.getModel().getPlayers().get(1).getNickname());
        assertNotEquals(model.getPlayers().get(0).getColor(), model.getPlayers().get(1).getColor());

        view.notify(new AddPlayerEvent(view, "intellimat"));
        assertEquals(3, controller.getModel().getPlayerNum());
        assertEquals("intellimat", controller.getModel().getPlayers().get(2).getNickname());

        view.notify(new AddPlayerEvent(view, "aghille"));
        assertEquals(4,controller.getModel().getPlayerNum());
        assertEquals("aghille", controller.getModel().getPlayers().get(3).getNickname());

        /*try
        {
            view.notify(new AddPlayerEvent(view, "non presente"));
            fail();

        }
        catch(Exception e)
        {

        }*/


    }
}
