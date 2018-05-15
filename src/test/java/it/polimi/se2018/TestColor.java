package it.polimi.se2018;

import it.polimi.se2018.utils.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestColor
{

    @Test
    //tests the parameters of all possible instance of the color with the getters
    public void testColorParameters()
    {
        Color colorRed = Color.RED;
        assertEquals(colorRed, Color.getColorFromString("red"));
        assertEquals("\u001b[31;1m", colorRed.getConsoleString());
        assertEquals('R', colorRed.getFirstChar());
        assertEquals(0, colorRed.getNum());

        Color colorBlue = Color.BLUE;
        assertEquals(colorBlue, Color.getColorFromString("blue"));
        assertEquals("\u001b[34;1m", colorBlue.getConsoleString());
        assertEquals('B', colorBlue.getFirstChar());
        assertEquals(1, colorBlue.getNum());

        Color colorGreen = Color.GREEN;
        assertEquals(colorGreen, Color.getColorFromString("green"));
        assertEquals("\u001b[32;1m", colorGreen.getConsoleString());
        assertEquals('G', colorGreen.getFirstChar());
        assertEquals(2, colorGreen.getNum());

        Color colorYellow = Color.YELLOW;
        assertEquals(colorYellow, Color.getColorFromString("yellow"));
        assertEquals("\u001b[33;1m", colorYellow.getConsoleString());
        assertEquals('Y', colorYellow.getFirstChar());
        assertEquals(3, colorYellow.getNum());

        Color colorPurple = Color.PURPLE;
        assertEquals(colorPurple, Color.getColorFromString("purple"));
        assertEquals("\u001b[35;1m", colorPurple.getConsoleString());
        assertEquals('P', colorPurple.getFirstChar());
        assertEquals(4, colorPurple.getNum());


        assertEquals(null, Color.getColorFromString("zorba"));

    }

    @Test
    public void testBlankColor()
    {
        assertEquals("\u001b[0m", Color.getResetConsoleString());
    }


}
