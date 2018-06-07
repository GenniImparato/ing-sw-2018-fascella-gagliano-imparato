package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.DeepShadesCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class DeepShadesPublicCard
 * @author Carmelo Fascella
 */
public class TestDeepShadesPublicCards
{
    private static PublicObjectiveCardScorer scorer;
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private static Die die0;
    private static Die die1;
    private static Die die2;
    private static Die die3;

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

        die0 = new Die(Color.BLUE);
        die0.setValue(5);
        die1 = new Die(Color.YELLOW);
        die1.setValue(6);
        die2 = new Die(Color.RED);
        die2.setValue(5);
        die3 = new Die(Color.BLUE);
        die3.setValue(1);


        try                     //try to add one "6" and two "5" and one die of another value
        {
            board.addDie(die0,1,0, false, true, false);
            board.addDie(die1,0,1, false, true, false);
            board.addDie(die2,0,2, false, true, false);
            board.addDie(die3,1,3, false, true, false);


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
     * Test if the scorer calculates the score related to one single set formed by one 6-die and one 5-die
     */
    @Test
    public void testScore()
    {
        assertEquals(2, new DeepShadesCard().acceptVisitor(scorer));
    }

}
