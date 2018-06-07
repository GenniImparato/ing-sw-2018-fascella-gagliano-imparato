package it.polimi.se2018;
import static org.junit.Assert.*;

import it.polimi.se2018.model.Model;
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
    public void testBuilder()
    {
        assertEquals(0, model.getPlayerNum());
        assertNotEquals(null, model.getDraftPool());
        assertNotEquals(null, model.getRoundTrack());
        assertEquals(3, model.getPublicObjectiveCards().size());
    }

    /**
     * It tests the behaviour of a cloned model
     */
    @Test
    public void testCloneBuilder()
    {
        assertEquals(model.getPlayerNum(), clonedModel.getPlayerNum());
        assertEquals(model.getDraftPool().getAllDice(), clonedModel.getDraftPool().getAllDice());
        assertEquals(model.getRoundTrack().getDiceAtRound(8), clonedModel.getRoundTrack().getDiceAtRound(8));
        assertEquals(model.getDiceBag().pullDice(5).size(), clonedModel.getDiceBag().pullDice(5).size());
        assertEquals(model.getPublicObjectiveCards().size(), clonedModel.getPublicObjectiveCards().size());
    }
}
