package it.polimi.se2018;
import it.polimi.se2018.model.Board;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard
{
    @Test
    //test if the cells of the board are initialized
    public void testParameters ()
    {
        Board board = new Board();

        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                assertNotNull(board.getCell(i,j));
            }
        }

    }

}
