package it.polimi.se2018;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.*;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class Board
 * @author Carmelo Fascella
 */
public class TestBoard
{
    private Board board;
    private SagradaSchemeCardFile sagradaSchemeCardFile;


    /**
     * Method that generates a board that will be used by the test methods
     */
    @Before
    public void setUp()
    {
        try                                             //try to open a known board
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Sun Catcher.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }

        catch (Exception e)
        {
            fail();
        }

    }


    /**
     * This method tests all the possible ways to add a die on the board.
     * It tries to add a die out of the range of the board, and it fails.
     * It tries to add a die whose color doesn't respect the cell restriction, and it fails.
     * It tries to add a die whose value doesn't respect the cell restriction, and it fails.
     * It tries to add the first die not on the border of the board, and it fails.
     * It tries to add the first die in the right position, and it succeeds.
     * It tries to add a die with the same value of the adjacent, and it fails.
     * It tries to add a die with the same color of the adjacent, and it fails.
     * It tries to add a die in a position not free, and it fails.
     * It tries to add a die, that it's not the first one, with an empty list of adjacent dice, and it fails.
     * It tries to add the second die on a cell with a color restriction that matches with its color, and it succeeds.
     * It tries to add another die on a cell with cell restriction that matches with its value, and it succeeds
     */
    @Test
    public void testAddDie()
    {


        Die die = new Die(Color.getRandomColor());
        try
        {
            board.addDie(die, -1,-1, false, false, false);
            fail();
        }
        catch (ChangeModelStateException|ActionNotPossibleException e)
        {

        }


        Die die0 = new Die(Color.RED);
        try
        {
            board.addDie(die0, 0,1, false, false, false);
            fail();
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {

        }


        Die die1 = new Die(Color.getRandomColor());
        die1.setValue(3);
        try
        {
            board.addDie(die1, 0, 2, false, false, false);
            fail();
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {

        }

        Die die2 = new Die(Color.getRandomColor());
        try
        {
            board.addDie(die2, 1,2, false, false, false);
            fail();
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {

        }


        Die die3 = new Die(Color.PURPLE);
        die3.setValue(6);
        try
        {
            board.addDie(die3, 0, 0, false, false, false);
        }
        catch (ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }


        Die die4 = new Die(Color.RED);
        die4.setValue(6);
        try
        {
            board.addDie(die4,1,0, false, false, false);
            fail();
        }

        catch (ChangeModelStateException|ActionNotPossibleException e)
        {

        }


        Die die5 = new Die(Color.PURPLE);
        die5.setValue(4);
        try
        {
            board.addDie(die5,1,0, false, false, false);
            fail();
        }

        catch (ChangeModelStateException|ActionNotPossibleException e)
        {

        }

        Die die6 = new Die(Color.getRandomColor());
        try
        {
            board.addDie(die6, 0, 0, false, false, false);
            fail();
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {

        }

        Die die7 = new Die(Color.getRandomColor());
        try
        {
            board.addDie(die7, 3, 2, false, false, false);
            fail();
        }
        catch (ChangeModelStateException|ActionNotPossibleException e)
        {

        }


        Die die8 = new Die(Color.BLUE);
        die8.setValue(5);
        try
        {
            board.addDie(die8, 0,1, false, false, false);
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }

        Die die9 = new Die(Color.YELLOW);
        die9.setValue(4);
        try
        {
            board.addDie(die9, 1,1, false, false, false);

        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }



    }

    /**
     * This method tests if the cells of the board generated random are initialized
     */
    @Test
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

    /**
     * This method tests the construction of a cloned board, with a given parameter
     */
    @Test
    public void testCloneBoard()
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();
        Cell [][] cellMatrix = new Cell[Board.ROWS][Board.COLUMNS];

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Firmitas.sagradaschemecard");
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


        Die die = new Die(Color.RED);           //add a die in the board that will be cloned, to test if it will be added
        die.setValue(6);                        //also in the cloned one.
        try
        {
            board.addDie(die, 3, 0, false, true, false);
        }
        catch (ChangeModelStateException|ActionNotPossibleException e)
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

    /**
     * This method tests the construction of a board
     */
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


    /**
     * This method tests the ways to move a die with a tool card, ignoring the value/color restrictions.
     */
    @Test
    public void testMoveDie()
    {

        Die die = new Die(Color.BLUE);
        die.setValue(6);
        Die die1 = new Die(Color.RED);
        die1.setValue(2);
        Die die2 = new Die(Color.getRandomColor());

        try                                             //try to add two dice on the board
        {
            board.addDie(die,0,1, false, true, false);
            board.addDie(die1,0,2, false, true, false);
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }


        try                                             //try to move the die ignoring the color restriction
        {
            board.moveDie(die, 1, 3, false, true, false);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

        Die die0 = new Die(Color.RED);
        die0.setValue(2);
        try                                             //try to move the die ignoring the value restriction
        {
            board.moveDie(die,1,1,true,false, false);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

        try                                             //try to move a die in a cell already occupied
        {
            board.moveDie(die1,0,1,true,false, false);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }

        try                                         //try to move a die that is not on the board
        {
            board.moveDie(die2, 0,3, true, true, false);
            fail();
        }
        catch(ChangeModelStateException e)
        {

        }


    }


    /**
     * This method tests if the getSchemeCardName() returns the right name of the board.
     */
    @Test
    public void testGetString()
    {
        try                                             //try to open a known board
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Aurora Sagradis.sagradaschemecard");

            assertEquals("Aurora Sagradis", sagradaSchemeCardFile.generateBoard().getSchemeCardName());
        }

        catch (Exception e)
        {
            fail();
        }


    }


    /**
     * Tests the possible cases where the methods analyzes wrong coordinates
     * Tests if the size of the array of the dice on a given row/column over the range, is empty
     */
    @Test
    public void testWrongCoordinates()
    {


        assertEquals(null, board.getCell(-1,-8));

        assertEquals(0, board.getDiceOnRow(-1).size());

        assertEquals(0, board.getDiceOnRow(9).size());

        assertEquals(0, board.getDiceOnColumn(-4).size());

        assertEquals(0, board.getDiceOnColumn(8).size());

        assertEquals(0, board.getDiagonalAdjacentDice(-1,-3).size());







    }

    /**
     * Tests how the methods react if a die is not placed on the board
     */

    @Test
    public void testDieNotPlaced()
    {
        Die die = new Die(Color.getRandomColor());
        Board board = new Board();

        assertEquals(-1, board.getDieRow(die));
        assertEquals(-1, board.getDieColumn(die));


    }

}
