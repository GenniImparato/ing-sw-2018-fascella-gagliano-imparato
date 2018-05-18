package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.MediumShadesCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestMediumShadesPublicCards
{

    private static PublicObjectiveCardScorer scorer;
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private static Die die0;
    private static Die die1;
    private static Die die2;
    private static Die die3;

    @BeforeClass
    public static void setUpClass()
    {
        board = new Board();

        try                         //try to add some dice on the board to verify if the method score returns the right value
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        die0 = new Die(Color.PURPLE);
        die0.setValue(3);
        die1 = new Die(Color.PURPLE);
        die1.setValue(4);
        die2 = new Die(Color.PURPLE);
        die2.setValue(3);
        die3 = new Die(Color.PURPLE);
        die3.setValue(1);


        try                     //try to add one "4" and two "3" and one die of another value
        {
            board.addDie(die0,0,0);
            board.addDie(die1,1,1);
            board.addDie(die2,2,2);
            board.addDie(die3,3,3);


        }
        catch(ChangeModelStateException e)
        {
            fail();
        }
    }

    @Before
    public void setUp()
    {
        scorer = new PublicObjectiveCardScorer(board);
    }

    @Test
    public void testScore()
    {
        assertEquals(2, new MediumShadesCard().acceptVisitor(scorer));
    }
}
