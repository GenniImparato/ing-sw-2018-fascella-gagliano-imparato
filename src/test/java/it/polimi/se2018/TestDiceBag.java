package it.polimi.se2018;

import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.model.Die;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class DiceBag
 * @author Carmelo Fascella
 */
public class TestDiceBag
{
    private DiceBag diceBag;
    private DiceBag clonedDiceBag;

    /**
     * Sets up the
     */
    @Before
    public void setUp()
    {
        diceBag = new DiceBag();
        clonedDiceBag = new DiceBag(diceBag);
    }

    @Test
    public void testCloneBuilder()
    {
        assertEquals(diceBag.pullDice(90).size(), clonedDiceBag.pullDice(90).size() );

    }
}
