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


public class TestSagradaSchemeCardFile
{

    @Test
    public void testTooElements()           //test if a file with more than 21 elements catch the InvalidFileException
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;

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

    @Test
    public void testElementTooBig()     //test if a file contains a value >6
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;

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

    @Test
    public void testElementTooLow()     //test if a file contains a value <0
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;

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

    @Test
    public void testFewElements()           //test if a file contains less than 21 elements
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;

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

    @Test
    public void testWrongColorName()        //test if a file contains a string that doesn't match with a color
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;

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

    @Test
    public void testNotValidName()
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;

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

    @Test
    public void testGetDifficulty()
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();
        int x = 0;

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            x=sagradaSchemeCardFile.generateBoard().getDifficulty();

        }
        catch(InvalidFileException e)
        {
            System.out.println("This board has a value out of range.sagradaschemecard");
        }
        catch(CannotReadFileException e)
        {
            fail();
        }


        assertEquals(5, x);

    }


}
