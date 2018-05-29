package it.polimi.se2018;

import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TestDraftPool
{
    private DiceBag diceBag;
    private DraftPool draftPool;

    /**
     * Set the draftpool and the dicebag that will be need while testing.
     */
    @Before
    public void setUp()
    {
        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
    }

    /**
     * If a die is added then it will be present in the List of dice of the draftpool
     */
    @Test
    public void testAddDie()
    {
        Die die = new Die(Color.getRandomColor());
        draftPool.addDie(die);
        assert draftPool.contains(die);
        assertEquals(die,draftPool.getAllDice().get(0));
    }

    /**
     * Tests if a die is present in the draftpool or not in three cases:
     * case 1: the draftpool is empty so contains() should return false
     * case 2: the draftpool has the die so contains() should return true
     * case 3: the draftpool is not empty but it doesn't contain the die so
     *          contains() should return false
     */
    @Test
    public void testContains()
    {
        Die redDie = new Die(Color.RED);
        Die greenDie = new Die(Color.GREEN);

        //the draftpool is empty so contains() should return false
        Assert.assertEquals(false,draftPool.contains(redDie));

        //The draftpool has the die so contains() should return true
        draftPool.addDie(redDie);
        Assert.assertEquals(true, draftPool.contains(redDie));

        //the draftpool is not empty but it doesn't contain the die
        Assert.assertEquals(false,draftPool.contains(greenDie));
    }

    /**
     * Tests if a die is correctly removed from the draftpool
     */
    @Test
    public void testDraftDie()
    {
       try {
            Die die = new Die(Color.getRandomColor());
            draftPool.addDie(die);
            Die draftedDie = draftPool.draftDie(0);
            assertEquals(die,draftedDie);
        }
        catch
                (ChangeModelStateException e){fail();}


        try
        {
            draftPool.draftDie(-1);
            fail();
        }
        catch(ChangeModelStateException e){}

        try
        {
            draftPool.draftDie(100);
            fail();
        }
        catch(ChangeModelStateException e){}
    }

}
