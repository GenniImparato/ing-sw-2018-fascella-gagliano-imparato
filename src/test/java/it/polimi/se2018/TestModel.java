package it.polimi.se2018;
import static org.junit.Assert.*;

import it.polimi.se2018.files.ToolCardsLoader;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import org.junit.Before;
import org.junit.Test;


/**
 * Class used to test the methods of the class Model
 * @author Carmelo Fascella
 */
public class TestModel
{
    private Model model;
    private Model clonedModel;
    private ToolCardsLoader loader;

    /**
     * It sets up a model and its cloned one
     */
    @Before
    public void setUp()
    {
        model = new Model();
        clonedModel = new Model(model);
    }

    /**
     * It tests that the model just built has no players,
     * and it has a dicebag, a draftpool and three Public Objective Cards
     */
    @Test
    public void testConstructor()
    {
        assertEquals(0, model.getPlayerNum());
        assertNotEquals(null, model.getDraftPool());
        assertNotEquals(null, model.getRoundTrack());
    }

    /**
     * It tests the behaviour of a cloned model
     */
    @Test
    public void testCloneConstructor()
    {
        assertEquals(model.getPlayerNum(), clonedModel.getPlayerNum());
        assertEquals(model.getDraftPool().getAllDice(), clonedModel.getDraftPool().getAllDice());
        assertEquals(model.getRoundTrack().getDiceAtRound(8), clonedModel.getRoundTrack().getDiceAtRound(8));
        assertEquals(model.getDiceBag().pullDice(5).size(), clonedModel.getDiceBag().pullDice(5).size());

    }

    /**
     * Test the case when we ask to get a wrong type of Die
     */
    @Test
    public void testWrongGetDie()
    {
        assertEquals(null, model.getDie(3));
    }

    /**
     * Test that tries to add a new Player and then to remove it, measuring the size of the Players
     */
    @Test
    public void testRemovePlayer()
    {
        try
        {
            model.addNewPlayer("Player1");
        }
        catch(ChangeModelStateException e)
        {

        }

        assertEquals(1, model.getPlayers().size());

        try
        {
            model.removePlayer("Player1");
        }
        catch(ChangeModelStateException e)
        {

        }

        assertEquals(0, model.getPlayers().size());


    }

    @Test
    public void testToolCard()
    {
        try
        {
            model.setCurrentToolCard(-1);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }


    }






}
