package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.ColumnColorVarietyCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class ColumnColorVarietyPublicCard
 * @author Carmelo Fascella
 */
public class TestColumnColorVarietyPublicCards
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
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        die0 = new Die(Color.PURPLE);
        die0.setValue(1);
        die1 = new Die(Color.YELLOW);
        die1.setValue(5);
        die2 = new Die(Color.RED);
        die2.setValue(3);
        die3 = new Die(Color.BLUE);
        die3.setValue(5);

        die4 = new Die(Color.YELLOW);
        die4.setValue(6);
        die5 = new Die(Color.PURPLE);
        die5.setValue(3);
        die6 = new Die(Color.YELLOW);
        die6.setValue(2);
        die7 = new Die(Color.RED);
        die7.setValue(1);


        try
        {
            board.addDie(die0,0,0, false, false, false);
            board.addDie(die1,1,0, false, false, false);
            board.addDie(die2,2,0, false, false, false);
            board.addDie(die3,3,0, false, false, false);

            board.addDie(die4,0,1, false, false, false);
            board.addDie(die5,1,1, false, false, false);
            board.addDie(die6,2,1, false, false, false);
            board.addDie(die7,3,1, false, false, false);
        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
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
     * Test if the scorer calculates the score related to one column with all different colors
     */
    @Test
    public void testScore()
    {
        assertEquals(5, new ColumnColorVarietyCard().acceptVisitor(scorer));
    }
}
