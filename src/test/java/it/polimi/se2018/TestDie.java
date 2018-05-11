package it.polimi.se2018;
import static org.junit.Assert.*;

import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import org.junit.Test;

public class TestDie
{
    @Test
    //test if the inverted value is equal to 7 - the former one
    public void testInvert()
    {
        Die die = new Die(Color.getRandomColor());
        int x = die.getValue();

        try
        {
            die.invert();
        }
        catch (Exception e)
        {
            fail();
        }

        assertNotNull(die.getValue());
        assertEquals (die.getValue(), 7-x);
    }

    @Test
    //test if the parameters are instantiated
    public void testParameters()
    {
        Die die = null;
        int x = 0;

        try
        {
            die = new Die(Color.getRandomColor());
        }
        catch (Exception e)
        {
            fail();
        }

        x = die.getValue();
        assertNotNull(die);
        assertNotNull(die.getColor());
    }

}