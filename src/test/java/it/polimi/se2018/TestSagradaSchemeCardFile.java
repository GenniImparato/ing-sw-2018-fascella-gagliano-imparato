package it.polimi.se2018;

import it.polimi.se2018.files.SagradaSchemeCardFile;

import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.model.Board;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


/**
 * Class used to test the methods of the class SagradaSchemeCardFile
 * @author Carmelo Fascella
 */
public class TestSagradaSchemeCardFile
{
    private SagradaSchemeCardFile sagradaSchemeCardFile;

    /**
     * Tests if a file with more than 21 elements catches the InvalidFileException
     */
    @Test
    public void testTooElements()
    {


        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("test_resources/schemecards/22 elem.sagradaschemecard");
            fail();
        }
        catch(InvalidFileException e)
        {
            System.out.println("This board contains more than 21 elements");
        }
        catch(CannotReadFileException e)
        {
            fail();
        }
    }

    /**
     * //Tests the case where a file contains a value >6
     */
    @Test
    public void testElementTooBig()
    {
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("test_resources/schemecards/out of range.sagradaschemecard");
            fail();
        }
        catch(InvalidFileException e)
        {
            System.out.println("This board has a value out of range.sagradaschemecard");
        }
        catch(CannotReadFileException e)
        {
            fail();
        }
    }

    /**
     * Tests the case where a file contains a value <0
     */
    @Test
    public void testElementTooLow()
    {
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("test_resources/schemecards/out of range 2.sagradaschemecard");
            fail();
        }
        catch(InvalidFileException e)
        {
            System.out.println("This board has a value out of range.sagradaschemecard");
        }
        catch(CannotReadFileException e)
        {
            fail();
        }
    }

    /**
     * //Tests the case where a file contains less than 21 elements
     */
    @Test
    public void testFewElements()
    {
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("test_resources/schemecards/20 elem.sagradaschemecard");
            fail();
        }
        catch(InvalidFileException e)
        {
            System.out.println("This board contains less than 21 elements");
        }
        catch(CannotReadFileException e)
        {
            fail();
        }
    }

    /**
     * Tests the case where a file contains a string that doesn't match with a color
     */
    @Test
    public void testWrongColorName()
    {
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("test_resources/schemecards/wrong color.sagradaschemecard");
            fail();
        }
        catch(InvalidFileException e)
        {
            System.out.println("This board contains a string that doesn't match with a color");
        }
        catch(CannotReadFileException e)
        {
            fail();
        }
    }

    /**
     * Test the case where the name of the file that you try to open is not valid
     */
    @Test
    public void testNotValidName()
    {
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("test_resources/schemecards/zuzulus");
            fail();
        }
        catch(InvalidFileException e)
        {
            fail();
        }
        catch(CannotReadFileException e)
        {
            System.out.println("This file is wrong");
        }
    }

    /**
     * Tests if the getter of the difficulty of a Board returns the last value of the file opened.
     */
    @Test
    public void testGetDifficulty()
    {
        int x = 0;

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Firmitas.sagradaschemecard");
            x=sagradaSchemeCardFile.generateBoard().getDifficulty();

        }
        catch(InvalidFileException e)
        {

        }
        catch(CannotReadFileException e)
        {
            fail();
        }


        assertEquals(5, x);

    }


}
