package it.polimi.se2018;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.*;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard
{
    private Board firstBoard = new Board();

    @Test
    //test if the cells of the board are initialized
    public void testRandomBoard ()
    {
        Board board = new Board();

        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                assertNotNull(board.getCell(i,j));
                assertEquals(null, board.getDie(i,j));
            }
        }

    }

    @Test
    //test if the cells of the board built with a given parameter is initialized
    public void testCloneBoard()
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();
        Cell [][] cellMatrix = new Cell[Board.ROWS][Board.COLUMNS];

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch (Exception e)
        {
            fail();
        }



        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                cellMatrix[i][j] = board.getCell(i,j);
            }
        }


        Die die = new Die(Color.RED);           //add a die in the board tha will be cloned, to test if it will be added
        die.setValue(6);                        //also in the cloned one.
        try
        {
            board.addDie(die, 3, 0);
        }
        catch (ChangeModelStateException e)
        {
            fail();
        }
        Board secondBoard = new Board (board);     //the input of the constructor is the board of the known one
        {
            for (int i=0; i<Board.ROWS; i++)
            {
                for (int j=0; j<Board.COLUMNS; j++)
                {
                    assertNotNull(secondBoard.getCell(i,j));
                }
            }

            assertNotNull(secondBoard.getDie(3,0));
        }


    }

    @Test
    public void testBoard()
    {
        Board board = new Board ();

        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                assertNotNull(board.getCell(i,j));
            }
        }
    }

    @Test
    public void testAddDie()
    {
        Board board = new Board();
        SagradaSchemeCardFile sagradaSchemeCardFile;

        try                                             //try to open a known board
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Sun Catcher.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }

        catch (Exception e)
        {
            fail();
        }


        Die die = new Die(Color.getRandomColor());
        try                                             //try to add a die out of the range of the board
        {
            board.addDie(die, -1,-1);
            fail();
        }
        catch (ChangeModelStateException e)
        {

        }


        Die die0 = new Die(Color.RED);                  //try to add a die whose color doesn't respect the cell restriction
        try
        {
            board.addDie(die0, 0,1);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }


        Die die1 = new Die(Color.getRandomColor());
        die1.setValue(3);
        try                                             //try to add a die whose value doesn't respect the cell restriction
        {
            board.addDie(die1, 0, 2);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }

        Die die2 = new Die(Color.getRandomColor());     //try to add the first die not on the border of the board
        try
        {
            board.addDie(die2, 1,2);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }


        Die die3 = new Die(Color.PURPLE);
        die3.setValue(6);
        try                                             //try to add the first die in the right position
        {
            board.addDie(die3, 0, 0);
        }
        catch (ChangeModelStateException e)
        {
            fail();
        }


        Die die4 = new Die(Color.RED);
        die4.setValue(6);
        try                                             //try to add a die with the same value of the adjacent
        {
            board.addDie(die4,1,0);
            fail();
        }

        catch (ChangeModelStateException e)
        {

        }


        Die die5 = new Die(Color.PURPLE);
        die5.setValue(4);
        try                                             //try to add a die with the same color of the adjacent
        {
            board.addDie(die5,1,0);
            fail();
        }

        catch (ChangeModelStateException e)
        {

        }

        Die die6 = new Die(Color.getRandomColor());
        try                                             //try to add a die in a position not free
        {
            board.addDie(die6, 0, 0);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }

        Die die7 = new Die(Color.getRandomColor());
        try                                             //try to add a die (that it's not the first) with an empty list of adjacent dice
        {
            board.addDie(die7, 3, 2);
            fail();
        }
        catch (ChangeModelStateException e)
        {

        }


        Die die8 = new Die(Color.BLUE);
        die8.setValue(5);
        try                                             //try to add the second die on a cell with color restriction
        {
            board.addDie(die8, 0,1);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

        Die die9 = new Die(Color.YELLOW);
        die9.setValue(4);
        try                                             //try to add another die on a cell with cell restriction
        {
            board.addDie(die9, 1,1);

        }
        catch(ChangeModelStateException e)
        {
            fail();
        }



    }

    /*@Test
    public void testMoveDie()
    {
        Board board = new Board();
        SagradaSchemeCardFile sagradaSchemeCardFile;

        try                                             //try to open a known board
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Sun Catcher.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }

        catch (Exception e)
        {
            fail();
        }


        Die die = new Die(Color.BLUE);
        die.setValue(6);
        try                                             //try to ignore the color restriction
        {
            board.moveDie(die, 3, 0, false, true);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        Die die0 = new Die(Color.RED);
        die0.setValue(2);
        try                                             //try to ignore the value restriction
        {
            board.moveDie(die0,3,1,true,false);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }


    }*/


    @Test
    public void testAddDieInRightWay()
    {

    }
    @Test
    public void testWrongCoordinates()
    {
        Board board = new Board();
        assertEquals(null, firstBoard.getCell(-1,-8));

    }

}
