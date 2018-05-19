package it.polimi.se2018;
import static org.junit.Assert.*;

import it.polimi.se2018.utils.Color;
import it.polimi.se2018.model.Die;
import org.junit.Before;
import org.junit.Test;

/**
 * Class used to test the methods of the class Die
 * @author Carmelo Fascella
 */
public class TestDie
{
    private Die die;
    private Die die1;
    private int x;

    /**
     * Sets up two dice that will be used in the tests
     */
    @Before
    public void setUp()
    {
        die = new Die(Color.getRandomColor());
        die1 = new Die(Color.getRandomColor());
        x = die.getValue();
    }

    /**
     * Tests the value of the opposite face of a Die
     */
    @Test
    public void testInvert()
    {
        die.invert();

        assertEquals (die.getValue(), 7-x);
    }

    /**
     * Test the construction of a cloned Die with a die already built passed by parameter
     */
    @Test
    public void testClonedDie()
    {
        Die clonedDie = new Die(die);

        assertEquals(clonedDie.getColor(), die.getColor());
        assertEquals(clonedDie.getValue(), die.getValue());

    }

    /**
     * Tries to increment the value of a die.
     * Tests the case where the value is 6 or more
     */
    @Test
    public void testIncrementDie()
    {
        die.setValue(4);
        die.incrementValue();

        assertEquals(5, die.getValue());

        die1.setValue(6);
        die1.incrementValue();

        assertEquals(6, die1.getValue());
    }

    /**
     * Tries to decrement the value of a die.
     * Tests the case where the value is 1 or less
     */
    @Test
    public void testDecrementDie()
    {
        die.setValue(3);
        die.decrementValue();

        assertEquals(2, die.getValue());

        die1.setValue(1);
        die1.decrementValue();

        assertEquals(1, die1.getValue());
    }

    /**
     * Test the case if you set a wrong value on the die, that is over the range
     */
    @Test
    public void testWrongSetValue()
    {
        die.setValue(3);
        assertEquals(3, die.getValue());

        die.setValue(-10);
        assertEquals(3, die.getValue());

        die.setValue(8);
        assertEquals(3, die.getValue());
    }

}
