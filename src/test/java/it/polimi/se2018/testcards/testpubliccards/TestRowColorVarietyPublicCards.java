package it.polimi.se2018.testcards.testpubliccards;


import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.RowColorVarietyCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class RowColorVarietyPublicCard
 * @author Carmelo Fascella
 */
public class TestRowColorVarietyPublicCards
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
    private static Die die8;
    private static Die die9;

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

        die0 = new Die(Color.PURPLE);
        die0.setValue(1);
        die1 = new Die(Color.GREEN);
        die1.setValue(6);
        die2 = new Die(Color.RED);
        die2.setValue(2);
        die3 = new Die(Color.YELLOW);
        die3.setValue(4);
        die4 = new Die(Color.BLUE);
        die4.setValue(3);

        die5 = new Die(Color.GREEN);
        die5.setValue(5);
        die6 = new Die(Color.PURPLE);
        die6.setValue(1);
        die7 = new Die(Color.GREEN);
        die7.setValue(3);
        die8 = new Die(Color.RED);
        die8.setValue(2);
        die9 = new Die(Color.YELLOW);
        die9.setValue(4);


        try                 //try to add one row with different colors, and the other with repeated colors
        {
            board.addDie(die0,0,0, false, false, false);
            board.addDie(die1,0,1, false, false, false);
            board.addDie(die2,0,2, false, false, false);
            board.addDie(die3,0,3, false, false, false);
            board.addDie(die4,0,4, false, false, false);

            board.addDie(die5,1,0, false, false, false);
            board.addDie(die6,1,1, false, false, false);
            board.addDie(die7,1,2, false, false, false);
            board.addDie(die8,1,3, false, false, false);
            board.addDie(die9,1,4, false, false, false);
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
     * Test if the scorer calculates the score related to one row with all different colors
     */
    @Test
    public void testScore()
    {
        assertEquals(6, new RowColorVarietyCard().acceptVisitor(scorer));
    }
}
