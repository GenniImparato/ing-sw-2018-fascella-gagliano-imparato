package it.polimi.se2018;

import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class Color
 * @author Carmelo Fascella
 */
public class TestColor
{
    private Color colorRed;
    private Color colorBlue;
    private Color colorPurple;
    private Color colorGreen;
    private Color colorYellow;

    /**
     *Sets Up the Color that will be used to test
     */
    @Before
    public void setUp()
    {
        colorRed = Color.RED;
        colorBlue = Color.BLUE;
        colorGreen = Color.GREEN;
        colorYellow = Color.YELLOW;
        colorPurple = Color.PURPLE;
    }

    /**
     * Test all the values of a Color.RED
     */
    @Test
    public void testRedColor()
    {

        assertEquals(colorRed, Color.getColorFromString("red"));
        assertEquals("\u001b[31;1m", colorRed.getConsoleString());
        assertEquals('R', colorRed.getFirstChar());
        assertEquals(0, colorRed.getNum());
    }

    /**
     * Test all the values of a Color.BLUE
     */
    @Test
    public void testBlueColor()
    {

        assertEquals(colorBlue, Color.getColorFromString("blue"));
        assertEquals("\u001b[34;1m", colorBlue.getConsoleString());
        assertEquals('B', colorBlue.getFirstChar());
        assertEquals(1, colorBlue.getNum());
    }

    /**
     * Test all the values of a Color.GREEN
     */
    @Test
    public void testGreenColor()
    {
        assertEquals(colorGreen, Color.getColorFromString("green"));
        assertEquals("\u001b[32;1m", colorGreen.getConsoleString());
        assertEquals('G', colorGreen.getFirstChar());
        assertEquals(2, colorGreen.getNum());
    }

    /**
     * Test all the values of a Color.YELLOW
     */
    @Test
    public void testYellowColor()
    {

        assertEquals(colorYellow, Color.getColorFromString("yellow"));
        assertEquals("\u001b[33;1m", colorYellow.getConsoleString());
        assertEquals('Y', colorYellow.getFirstChar());
        assertEquals(3, colorYellow.getNum());
    }

    /**
     * Test all the values of a Color.PURPLE
     */
    @Test
    public void testPurpleColor()
    {
        assertEquals(colorPurple, Color.getColorFromString("purple"));
        assertEquals("\u001b[35;1m", colorPurple.getConsoleString());
        assertEquals('P', colorPurple.getFirstChar());
        assertEquals(4, colorPurple.getNum());
    }

    /**
     * Test the value of a null Color
     */
    @Test
    public void testNullColor()
    {
        assertEquals(null, Color.getColorFromString("zorba"));
    }

    /**
     * Test the value of a Color.RED
     */
    @Test
    public void testBlankColor()
    {
        assertEquals("\u001b[0m", Color.getResetConsoleString());
    }


}
