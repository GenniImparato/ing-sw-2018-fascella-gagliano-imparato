package it.polimi.se2018;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.*;
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
    public void testBoard()
    {

        Cell [][] charMatrix = new Cell[Board.ROWS][Board.COLUMNS];

        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                charMatrix[i][j] = firstBoard.getCell(i,j);
                assertNotNull(firstBoard.getCell(i,j));

            }
        }

        Board secondBoard = new Board (charMatrix);     //the input of the constructor is generated random before
        {
            for (int i=0; i<Board.ROWS; i++)
            {
                for (int j=0; j<Board.COLUMNS; j++)
                {
                    assertNotNull(secondBoard.getCell(i,j));
                }
            }
        }


    }

    @Test
    public void testCloneBoard()
    {
        Board board = new Board ();
        Board clonedBoard = new Board(board);

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
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/1-Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }

        catch (Exception e)
        {
            fail();
        }

        Die die1 = new Die(Color.PURPLE);

        try
        {
            board.addDie(die1, 0, 0);
        }
        catch (CannotAddDieException e)
        {
            fail();
        }

        assertEquals(die1, board.getDie(0,0));
    }

    @Test
    public void testWrongCoordinates()
    {
        Board board = new Board();
        assertEquals(null, firstBoard.getCell(-1,-8));

    }

}
