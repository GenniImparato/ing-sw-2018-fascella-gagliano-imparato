package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.ColumnShadeVarietyCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class ColumnShadeVarietyShadePublicCard
 * @author Carmelo Fascella
 */
public class TestColumnShadeVarietyPublicCards
{
    private static PublicObjectiveCardScorer scorer;
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private static Die die0;
    private static Die die1;
    private static Die die2;
    private static Die die3;
    private static Die die4;
    private static Die die5;
    private static Die die6;
    private static Die die7;

    /**
     * Tries to add some dice on the board to verify if the scorer calculates the right value
     */
    @BeforeClass
    public static void setUpClass()
    {
        board = new Board();

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        die0 = new Die(Color.PURPLE);                       //dice in the first column (different values)
        die0.setValue(3);
        die1 = new Die(Color.RED);
        die1.setValue(5);
        die2 = new Die(Color.GREEN);
        die2.setValue(1);
        die3 = new Die(Color.PURPLE);
        die3.setValue(4);

        die4 = new Die(Color.BLUE);                         //dice in the second column (two with the same value)
        die4.setValue(6);
        die5 = new Die(Color.PURPLE);
        die5.setValue(1);
        die6 = new Die(Color.BLUE);
        die6.setValue(2);
        die7 = new Die(Color.GREEN);
        die7.setValue(1);

        Die die8 = new Die(Color.RED);                          //one single die on the third column to test one column not completed
        die8.setValue(5);

        try
        {
            board.addDie(die0,0,0);
            board.addDie(die1,1,0);
            board.addDie(die2,2,0);
            board.addDie(die3,3,0);

            board.addDie(die4,0,1);
            board.addDie(die5,1,1);
            board.addDie(die6,2,1);
            board.addDie(die7,3,1);

            board.addDie(die8,0,2);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }
    }

    /**
     * Creates the scorer with the board just created before
     */
    @Before
    public void setUp()
    {
        scorer = new PublicObjectiveCardScorer(board);
    }

    /**
     * Test if the scorer calculates the score related to one column with all different color values
     */
    @Test
    public void testScore()
    {
        assertEquals(4, new ColumnShadeVarietyCard().acceptVisitor(scorer));
    }
}
