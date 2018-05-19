package it.polimi.se2018;
import it.polimi.se2018.model.Cell;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class CellRestriction
 * @author Carmelo Fascella
 */
public class TestCellRestriction
{
    /**
     *Tests if the cell without restrictions complies with our convention
     */

    @Test
    public void testNoRestrictions()
    {
        Cell cell = new Cell();

        assertEquals(false, cell.getRestriction().isActive());
        assertEquals(null, cell.getRestriction().getColor());
        assertEquals(-1, cell.getRestriction().getValue());


    }

    /**
     *Tests if the cell with value restriction complies with our convention
     */

    @Test
    public void testValueRestriction()
    {
        Cell cell = new Cell(3);

        assertEquals(true, cell.getRestriction().isActive());
        assertEquals(true, cell.getRestriction().isValue());
        assertEquals(false, cell.getRestriction().isColor());
        assertEquals(null, cell.getRestriction().getColor());
        assertEquals(3, cell.getRestriction().getValue());


    }

    /**
     *Tests if the cell with color restriction complies with our convention
     */

    @Test
    public void testColorRestriction()
    {
        Cell cell = new Cell(Color.BLUE);

        assertEquals(true, cell.getRestriction().isActive());
        assertEquals(true, cell.getRestriction().isColor());
        assertEquals(false, cell.getRestriction().isValue());
        assertEquals(-1, cell.getRestriction().getValue());
        assertEquals(Color.BLUE, cell.getRestriction().getColor());

    }

    /**
     *Tests the construction of a cloned Cell, and tests if the values of the cloned cell restriction match with the original ones
     */

    @Test
    public void testCloneCellRestriction ()
    {
        Cell cell = new Cell(Color.GREEN);
        Cell cloneCell = new Cell (cell);

        assertEquals(cloneCell.getRestriction().isActive(), cell.getRestriction().isActive());
        assertEquals(cloneCell.getRestriction().isColor(), cell.getRestriction().isColor());
        assertEquals(cloneCell.getRestriction().isValue(), cell.getRestriction().isValue());
        assertEquals(cloneCell.getRestriction().getValue(), cell.getRestriction().getValue());
        assertEquals(cloneCell.getRestriction().getColor(), cell.getRestriction().getColor());

    }


}